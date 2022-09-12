package com.viktor.webcrawler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostDto {

    private Long id;

    private String domainUrl;

    private int outerLinks;

    private int numberOfPages;
}
