package com.sarac.sarac.user.repository;

import com.sarac.sarac.user.entitiy.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    User findOneByKakaoId(Long KakaoId);

    @Transactional
    void deleteUserByKakaoId(Long KakaoId);


}
