package com.sarac.sarac.review.repository;

import com.sarac.sarac.review.entity.ReviewHashtag;
import com.sarac.sarac.review.payload.response.ReviewHashtagDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewHashtagRepository extends JpaRepository<ReviewHashtag, Long> {

    ReviewHashtag save(ReviewHashtag reviewHashtag);

    List<ReviewHashtag> findAllByReviewId(Long reviewId);

    void deleteAllByReviewId(Long reviewId);

    @Query(value = "select rh.content as text, count(rh.content) as value from review_hashtag as rh join review as r on r.id=rh.review_id where r.writer=:userId group by rh.content",nativeQuery = true)
    List<ReviewHashtagDTO> findHashtagCnt(@Param("userId") Long userId);


}
