package com.sarac.sarac.cafe.repository;

import com.sarac.sarac.cafe.entity.Cafe;
import com.sarac.sarac.cafe.entity.CafeLike;
import com.sarac.sarac.user.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeLikeRepository extends JpaRepository<CafeLike, Long> {
    CafeLike save(CafeLike cafeLike);
    void deleteByCafeAndUser(Cafe cafe, User user);
    Optional<CafeLike> findOneByCafeAndUser(Cafe cafe, User user);
//    List<User> findAllByCafe(Cafe cafe);
//    List<User> findAllByCafeAndGoodOrBad(Cafe cafe, boolean goodOrBad);
//    Integer countByCafe(long cafeId);
}
