package com.viktor.webcrawler.controller;

import com.viktor.webcrawler.dto.PageDto;
import com.viktor.webcrawler.service.WebCrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/domain")
@RequiredArgsConstructor
public class WebCrawlerController {

    private final WebCrawlerService webCrawler;

    @GetMapping()
    public ResponseEntity<List<PageDto>> getHostInfo(@RequestParam String domainUrl) {
        List<PageDto> domainPages = webCrawler.getDomainInfo(domainUrl);
        return new ResponseEntity<>(domainPages, HttpStatus.OK);
    }

    @GetMapping("/pages/{hostId}")
    public ResponseEntity<List<PageDto>> getHostPages(@PathVariable Long hostId) {
        List<PageDto> pages = webCrawler.getDomainPages(hostId);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        webCrawler.deleteDomain(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
