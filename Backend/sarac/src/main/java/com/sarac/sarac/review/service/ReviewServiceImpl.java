package com.sarac.sarac.review.service;

import com.sarac.sarac.book.repository.BookRepository;
import com.sarac.sarac.global.util.FileUpload;
import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.entity.ReviewHashtag;
import com.sarac.sarac.review.entity.ReviewPhoto;
import com.sarac.sarac.review.payload.response.ReviewListDTO;

import com.sarac.sarac.review.payload.request.ReviewRequest;
import com.sarac.sarac.review.repository.ReviewHashtagRepository;
import com.sarac.sarac.review.repository.ReviewPhotoRepository;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService{

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final FileUpload fileUpload;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    private final ReviewHashtagRepository reviewHashtagRepository;

    private final ReviewPhotoRepository reviewPhotoRepository;

    private final JwtUtil jwtUtil;


    @Override
    public Long registerReview(ReviewRequest review, String authorization) {

        Review saveReview = new Review();
        saveReview.setBook(bookRepository.findOneByIsbn(review.getIsbn()));
        saveReview.setContent(review.getContent());
        saveReview.setTitle(review.getTitle());
        saveReview.setUser(userRepository.findOneById(review.getWriter()));
        saveReview.setBookScore(review.getBookScore());
        saveReview.setIsSecret(review.getIsSceret());

        Long id = reviewRepository.save(saveReview).getId();



        Set<String> hashtag = new HashSet<>();
        hashtag = review.getHashtag();
        for (String s : hashtag) {
            ReviewHashtag reviewHashtag = new ReviewHashtag();
            reviewHashtag.setReview(reviewRepository.findOneById(id));
            reviewHashtag.setContent(s);
            reviewHashtagRepository.save(reviewHashtag);
        }

        return id;
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
            ReviewPhoto reviewPhoto = new ReviewPhoto();
            reviewPhoto.setPhotoUrl(imagePath);
            reviewPhoto.setReview(reviewRepository.findOneById(id));
            reviewPhotoRepository.save(reviewPhoto);
        }

    }
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
