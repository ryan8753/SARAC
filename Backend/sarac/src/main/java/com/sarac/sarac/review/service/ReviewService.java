package com.sarac.sarac.review.service;

import com.sarac.sarac.review.payload.response.ReviewListDTO;
import com.sarac.sarac.review.payload.request.ReviewRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
    Long registerReview(ReviewRequest review, String token);

    void deleteReview(Long id);

    void updateReview(ReviewRequest review, Long id,List<MultipartFile> files) throws IOException;

    List<ReviewListDTO> showUserReviewList(String token);

    List<ReviewListDTO> showBookReviewList(String isbn);

    void uploadFile(List<MultipartFile> files, Long id) throws IOException;

}
