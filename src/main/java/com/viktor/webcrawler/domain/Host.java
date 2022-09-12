package com.viktor.webcrawler.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hosts")
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "domain_url")
    private String domainUrl;

    @Column(name = "outer_links")
    private int outerLinks;

    @Column(name = "number_of_pages")
    private int numberOfPages;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "host",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    @JsonIgnore
    private List<Page> pages = new ArrayList<>();

    public Host(String domainUrl, int outerLinks, int numberOfPages) {
        this.domainUrl = domainUrl;
        this.outerLinks = outerLinks;
        this.numberOfPages = numberOfPages;
    }

    public void addPage(Page page) {
       page.setHost(this);
       pages.add(page);
    }
}
