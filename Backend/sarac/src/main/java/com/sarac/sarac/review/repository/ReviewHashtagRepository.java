package com.sarac.sarac.review.repository;

import com.sarac.sarac.review.entity.ReviewHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewHashtagRepository extends JpaRepository<ReviewHashtag, Long> {

    ReviewHashtag save(ReviewHashtag reviewHashtag);

}
