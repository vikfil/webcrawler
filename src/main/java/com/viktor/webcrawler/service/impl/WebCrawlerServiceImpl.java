package com.viktor.webcrawler.service.impl;

import com.viktor.webcrawler.domain.Host;
import com.viktor.webcrawler.domain.Page;
import com.viktor.webcrawler.dto.PageDto;
import com.viktor.webcrawler.mapper.PageMapper;
import com.viktor.webcrawler.repository.HostRepository;
import com.viktor.webcrawler.repository.PageRepository;
import com.viktor.webcrawler.service.WebCrawlerService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class WebCrawlerServiceImpl implements WebCrawlerService {

    private final PageRepository pageRepository;

    private final HostRepository hostRepository;

    private final PageMapper pageMapper;

    private Set<String> visitedLinks = new HashSet<>();

    private int domainExternalLinks = 0;

    private List<Page> internalPages = new ArrayList<>();

    @Override
    public List<PageDto> getDomainInfo(String domainUrl) {
        int clickLevel = 1;
        Queue<List<String>> queue = new LinkedList<>();
        queue.offer(Collections.singletonList(domainUrl));
        visitedLinks.add(domainUrl);

        while (!queue.isEmpty()) {
            List<String> urls = queue.poll();
            List<String> linksOnSameLevel = new ArrayList<>();
            if (!urls.isEmpty()) {
                for (String url : urls) {
                    List<String> linksOnPage = traverse(url,domainUrl,clickLevel);
                    linksOnSameLevel.addAll(linksOnPage);
                }
                queue.offer(linksOnSameLevel);
            }
            clickLevel++;
        }
        Host host = persistHost(new Host(domainUrl,domainExternalLinks,internalPages.size()), internalPages);
        return getDomainPages(host.getId());
    }

    private List<String> traverse(String url, String domainUrl, int clickLevel) {
        List<String> childLinks = new ArrayList<>();
        Document document = requestToUrl(url);
        if (document != null) {
            Elements links = document.select("a[href]");
            List<String> innerLinks = getInnerLinks(links, domainUrl);
            int outerLinks= calculateOuterLinks(links, domainUrl);
            domainExternalLinks += outerLinks;
            internalPages.add(new Page(url, clickLevel, outerLinks));
            for (String path : innerLinks) {
                if (!visitedLinks.contains(path)){
                    childLinks.add(path);
                    visitedLinks.add(path);
                }
            }
        }
        return childLinks;
    }

    private Document requestToUrl(String url) {
        try {
            Connection connection = Jsoup.connect(url);
            return connection.get();
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private int calculateOuterLinks(Elements links, String domainUrl) {
        return (int) links.stream()
                .filter(link -> !link.absUrl("href").startsWith(domainUrl))
                .count();
    }

    private List<String> getInnerLinks(Elements links, String domainUrl) {
        return links.stream()
                .map(element -> element.absUrl("href"))
                .filter(link -> !visitedLinks.contains(link) && link.startsWith(domainUrl))
                .collect(Collectors.toList());
    }

    private Host persistHost(Host host, List<Page> domainPages) {
        for (Page page : domainPages) {
            host.addPage(page);
        }
        Host persistedHost = hostRepository.save(host);
        clearInternalState();
        return persistedHost;
    }

    @Override
    public List<PageDto> getDomainPages(Long hostId) {
        return pageMapper.toListDto(pageRepository.findAllPageByHostId(hostId));
    }

    @Override
    public void deleteDomain(Long id) {
        hostRepository.deleteById(id);
    }

    private void clearInternalState() {
        this.visitedLinks.clear();
        this.internalPages.clear();
        this.domainExternalLinks = 0;
    }
}

