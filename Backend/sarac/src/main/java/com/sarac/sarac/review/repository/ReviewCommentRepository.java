package com.sarac.sarac.review.repository;

import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    Integer countReviewCommentByReview(Review review);

    Optional<List<ReviewComment>> findAllByReview(Review review);
}
