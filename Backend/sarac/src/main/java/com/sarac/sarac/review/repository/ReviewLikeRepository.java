package com.sarac.sarac.review.repository;

import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.entity.ReviewLike;
import com.sarac.sarac.user.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {

    Integer countReviewLikeByReview(Review review);

    Boolean existsByUserAndReview(User user, Review review);

    ReviewLike findByUserAndReview(User user, Review review);

}
