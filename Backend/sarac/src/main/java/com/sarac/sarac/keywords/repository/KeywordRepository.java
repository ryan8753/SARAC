package com.sarac.sarac.keywords.repository;

import com.sarac.sarac.keywords.entity.Keyword;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findTop20ByOrderByCountDesc();
}
