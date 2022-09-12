package com.viktor.webcrawler.repository;

import com.viktor.webcrawler.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostRepository extends JpaRepository<Host, Long> {
}
