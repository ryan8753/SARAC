package com.sarac.sarac.review.repository;


import com.sarac.sarac.review.entity.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {

    ReviewPhoto save(ReviewPhoto reviewPhoto);
}
