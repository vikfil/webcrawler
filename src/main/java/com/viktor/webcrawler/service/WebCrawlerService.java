package com.viktor.webcrawler.service;

import com.viktor.webcrawler.dto.PageDto;
import java.util.List;

public interface WebCrawlerService {

    List<PageDto> getDomainInfo(String domainUrl);
    List<PageDto> getDomainPages(Long hostId);
    void deleteDomain(Long id);
}
