package com.sarac.sarac.review.repository;

import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.entity.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {

    Integer countReviewLikeByReview(Review review);

}
