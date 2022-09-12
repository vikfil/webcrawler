package com.viktor.webcrawler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pages")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "page_url")
    private String pageUrl;

    @Column(name = "click_level")
    private int clickLevel;

    @Column(name = "external_links")
    private int externalLinks;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host host;

    public Page(String pageUrl, int clickLevel, int externalLinks) {
        this.pageUrl = pageUrl;
        this.clickLevel = clickLevel;
        this.externalLinks = externalLinks;
    }
}
