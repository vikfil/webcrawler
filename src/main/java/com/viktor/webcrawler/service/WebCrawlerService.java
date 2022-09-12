package com.viktor.webcrawler.service;

import com.viktor.webcrawler.dto.PageDto;
import java.util.List;

public interface WebCrawlerService {
    public void saveDomainInfo(String domainUrl);
    public List<PageDto> getDomainPages();
    public void deleteDomain(Long id);
}
