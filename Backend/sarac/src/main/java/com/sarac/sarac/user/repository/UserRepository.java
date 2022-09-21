package com.sarac.sarac.user.repository;

import com.sarac.sarac.user.entitiy.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    User findOneByKakaoId(Long KakaoId);

    User findOneById(Long id);

    @Transactional
    void deleteUserByKakaoId(Long KakaoId);

    List<User> findByNicknameContaining(String nickname);
    List<User> findByIdContaining(Long id);


}
