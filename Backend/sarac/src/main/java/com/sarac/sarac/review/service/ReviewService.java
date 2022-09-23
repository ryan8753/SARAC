package com.sarac.sarac.review.service;

import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.payload.request.ReviewCommentRequest;
import com.sarac.sarac.review.payload.response.RandomReviewDTO;
import com.sarac.sarac.review.payload.response.ReviewDTO;
import com.sarac.sarac.review.payload.response.ReviewDetailDTO;
import com.sarac.sarac.review.payload.response.ReviewListDTO;
import com.sarac.sarac.review.payload.request.ReviewRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ReviewService {
    Long registerReview(ReviewRequest review);

    void deleteReview(Long id);

    void updateReview(ReviewRequest review, Long id,List<MultipartFile> files) throws IOException;

    ReviewDTO showReview(long id);

    List<ReviewListDTO> showBookReviewList(String isbn);

    void uploadReviewFile(List<MultipartFile> files, Long id) throws IOException;

    ReviewDetailDTO showDetailReview(long id);

    Long registComment(ReviewCommentRequest reviewComment);

    List<RandomReviewDTO> showRandomFeeds(Map<String, Object> token);

}
