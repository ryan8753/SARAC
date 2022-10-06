package com.sarac.sarac.cafe.repository;

import com.sarac.sarac.cafe.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
    List<Cafe> findAllByLatitudeGreaterThanEqualAndLatitudeLessThanEqualAndLongitudeGreaterThanEqualAndLongitudeLessThanEqual(double minLatitude, double maxLatitude, double minLongitude, double maxLongitude);
    Optional<Cafe> findById(long id);
}
