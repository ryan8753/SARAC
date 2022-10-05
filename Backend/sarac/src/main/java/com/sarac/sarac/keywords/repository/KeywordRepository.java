package com.sarac.sarac.keywords.repository;

import com.sarac.sarac.keywords.entity.Keyword;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findByCountGreaterThan(int count, Pageable pageable);
}
