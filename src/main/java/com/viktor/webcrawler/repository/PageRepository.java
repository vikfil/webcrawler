package com.viktor.webcrawler.repository;

import com.viktor.webcrawler.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Long> {
    @Query("SELECT p FROM Page p WHERE p.host.id = :hostId")
    List<Page> findAllPageByHostId(Long hostId);
}
