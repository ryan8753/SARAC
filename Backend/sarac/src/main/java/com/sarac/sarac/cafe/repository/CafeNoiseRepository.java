package com.sarac.sarac.cafe.repository;

import com.sarac.sarac.cafe.entity.Cafe;
import com.sarac.sarac.cafe.entity.CafeNoise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeNoiseRepository extends JpaRepository<CafeNoise, Long> {
    CafeNoise save(CafeNoise cafeNoise);
    Optional<CafeNoise> findFirst1ByCafeOrderByCreatedAtDesc(Cafe cafe);

    List<CafeNoise> findAllByCafe(Cafe cafe);


    Integer countByUserId(Long userId);



}
