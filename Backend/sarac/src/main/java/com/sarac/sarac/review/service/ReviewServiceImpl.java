package com.sarac.sarac.review.service;

import com.sarac.sarac.book.repository.BookRepository;
import com.sarac.sarac.global.util.FileUpload;
import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.entity.ReviewHashtag;
import com.sarac.sarac.review.entity.ReviewPhoto;
import com.sarac.sarac.review.payload.response.ReviewDTO;
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
    public Long registerReview(ReviewRequest review) {

        Review saveReview = new Review();
        saveReview.setBook(bookRepository.findOneByIsbn(review.getIsbn()));
        saveReview.setContent(review.getContent());
        saveReview.setTitle(review.getTitle());
        saveReview.setUser(userRepository.findOneById(review.getWriter()));
        saveReview.setBookScore(review.getBookScore());
        saveReview.setIsSecret(review.getIsSecret());

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
        List<ReviewPhoto> reviewPhotos = reviewPhotoRepository.findAllByReviewId(id);
        for (ReviewPhoto reviewPhoto : reviewPhotos) {
            fileUpload.fileDelete("review",reviewPhoto.getPhotoUrl());
        }
        reviewRepository.deleteReviewById(id);

    }

    @Override
    @Transactional
    public void updateReview(ReviewRequest review,Long reviewId,List<MultipartFile> files) throws IOException {


        Review originReview = reviewRepository.findOneById(reviewId);
        if(review.getIsbn()!=null) {
            originReview.setBook(bookRepository.findOneByIsbn(review.getIsbn()));
        }
        if(review.getContent()!=null) {
            originReview.setContent(review.getContent());
        }
        if(review.getTitle()!=null) {
            originReview.setTitle(review.getTitle());
        }
        if(review.getBookScore()!=null) {
            originReview.setBookScore(review.getBookScore());
        }
        if(review.getIsSecret()!=null) {
            originReview.setIsSecret(review.getIsSecret());
        }

        Review saveReview=reviewRepository.save(originReview);

        reviewHashtagRepository.deleteAllByReviewId(reviewId);

        Set<String> hashtag = new HashSet<>();
        hashtag = review.getHashtag();
        for (String s : hashtag) {
            ReviewHashtag reviewHashtag = new ReviewHashtag();
            reviewHashtag.setReview(saveReview);
            reviewHashtag.setContent(s);
            reviewHashtagRepository.save(reviewHashtag);
        }

        if(files!=null){

            List<ReviewPhoto> reviewPhotos = reviewPhotoRepository.findAllByReviewId(reviewId);
            for (ReviewPhoto reviewPhoto : reviewPhotos) {
                fileUpload.fileDelete("review",reviewPhoto.getPhotoUrl());
            }
            reviewPhotoRepository.deleteAllByReviewId(reviewId);
            uploadReviewFile(files, reviewId);
        }

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
    public ReviewDTO showReview(long reviewId) {

        Review review = reviewRepository.findOneById(reviewId);
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setBookScore(review.getBookScore());
        reviewDTO.setIsbn(review.getBook().getIsbn());
        reviewDTO.setContent(review.getContent());
        reviewDTO.setTitle(review.getTitle());
        reviewDTO.setIsSecret(review.getIsSecret());


        return reviewDTO;
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
    public void uploadReviewFile(List<MultipartFile> files, Long id) throws IOException {

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
