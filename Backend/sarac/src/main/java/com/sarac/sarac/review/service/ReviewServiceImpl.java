package com.sarac.sarac.review.service;

import com.sarac.sarac.global.util.FileUpload;
import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.payload.dto.ReviewListDTO;

import com.sarac.sarac.review.payload.request.ReviewRequest;
import com.sarac.sarac.review.repository.ReviewRepository;
import com.sarac.sarac.user.repository.UserRepository;
import com.sarac.sarac.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService{

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final FileUpload fileUpload;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;


    @Override
    public Long registerReview(ReviewRequest review, String authorization) {


//        Long id = reviewRepository.save(saveReview).getId();
//        return id;
        return null;

    }

    @Override
    @Transactional
    public void deleteReview(Long id) {

        reviewRepository.deleteReviewById(id);
    }

    @Override
    @Transactional
    public void updateReview(ReviewRequest review, Long id) {

    }

    @Override
    public List<ReviewListDTO> showUserReviewList(String token) {
        List<Review> ReviewList = reviewRepository.findAllByUserId(userRepository.findOneByKakaoId((Long)jwtUtil.parseJwtToken(token).get("id")).getId());
        List<ReviewListDTO> reviewListDTO = new ArrayList<>();
        for (Review review : ReviewList) {
            ReviewListDTO reviewListDTO1 = new ReviewListDTO();
            reviewListDTO1.setBookTitle(review.getBook().getBookTitle());
            reviewListDTO1.setTitle(review.getTitle());

            reviewListDTO.add(reviewListDTO1);
        }
        return reviewListDTO;
    }

    @Override
    public List<ReviewListDTO> showBookReviewList(String isbn) {

        List<Review> ReviewList = reviewRepository.findAllByBookIsbn(isbn);
        List<ReviewListDTO> reviewListDTO = new ArrayList<>();
        for (Review review : ReviewList) {
            ReviewListDTO reviewListDTO1 = new ReviewListDTO();
            reviewListDTO1.setBookTitle(review.getBook().getBookTitle());
            reviewListDTO1.setTitle(review.getTitle());

            reviewListDTO.add(reviewListDTO1);
        }
        return reviewListDTO;
    }

    @Override
    public void uploadFile(List<MultipartFile> files, Long id) throws IOException {

        for (MultipartFile file : files) {
            String imagePath = fileUpload.fileUpload(file,id,"review");


        }




//        User user = userRepository.findOneByKakaoId(kakaoId);
//        user.setImagePath(newImagePath);
//        userRepository.save(user);

    }
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
