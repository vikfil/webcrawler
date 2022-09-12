package com.viktor.webcrawler.repository;

import com.viktor.webcrawler.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Long> {
}
