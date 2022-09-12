package com.viktor.webcrawler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto {

    private Long id;

    private String pageUrl;

    private int clickLevel;

    private int externalLinks;

    private HostDto host;
}
