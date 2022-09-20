package com.sarac.sarac.review.service;

import com.sarac.sarac.review.dto.ReviewListDTO;
import com.sarac.sarac.review.entity.Review;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
    Long registerReview(Review review, String token);

    void deleteReview(Long id);

    void updateReview(Review review, Long id);

    List<ReviewListDTO> showUserReviewList(String token);

    List<ReviewListDTO> showBookReviewList(String isbn);

    void uploadFile(List<MultipartFile> files, Long id) throws IOException;

}
