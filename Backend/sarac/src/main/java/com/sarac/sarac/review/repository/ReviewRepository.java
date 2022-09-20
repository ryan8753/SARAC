package com.sarac.sarac.review.repository;

import com.sarac.sarac.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review save(Review review);

    List<Review> findAllByUserId(Long id);

    Review findOneById(Long id);

    List<Review> findAllByBookIsbn(String isbn);


    @Transactional
    void deleteReviewById(Long id);



}
