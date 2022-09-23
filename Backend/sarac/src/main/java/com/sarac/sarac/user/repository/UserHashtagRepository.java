package com.sarac.sarac.user.repository;

import com.sarac.sarac.user.entitiy.UserHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHashtagRepository extends JpaRepository<UserHashtag, Long> {
    List<UserHashtag> findByUserId(Long userId);
}
