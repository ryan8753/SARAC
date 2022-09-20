package com.sarac.sarac.review.repository;

import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.entity.ReviewHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewHashtagRepository extends JpaRepository<ReviewHashtag, Long> {

    ReviewHashtag save(ReviewHashtag reviewHashtag);

    List<ReviewHashtag> findAllByReviewId(Long id);


}
