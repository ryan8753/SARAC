package com.sarac.sarac.review.service;

import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.book.repository.BookRepository;
import com.sarac.sarac.global.util.FileUpload;
import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.library.repository.LibraryRepository;
import com.sarac.sarac.library.type.LibraryType;
import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.entity.ReviewComment;
import com.sarac.sarac.review.entity.ReviewHashtag;
import com.sarac.sarac.review.entity.ReviewPhoto;
import com.sarac.sarac.review.payload.request.ReviewCommentRequest;
import com.sarac.sarac.review.payload.response.*;

import com.sarac.sarac.review.payload.request.ReviewRequest;
import com.sarac.sarac.review.repository.*;
import com.sarac.sarac.user.entitiy.User;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService{

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final FileUpload fileUpload;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    private final LibraryRepository libraryRepository;

    private final ReviewHashtagRepository reviewHashtagRepository;

    private final ReviewPhotoRepository reviewPhotoRepository;

    private final ReviewLikeRepository reviewLikeRepository;

    private final ReviewCommentRepository reviewCommentRepository;
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


    @Override
    public ReviewDetailDTO showDetailReview(long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow();
        ReviewDetailDTO reviewDetailDTO = new ReviewDetailDTO();
        reviewDetailDTO.setId(reviewId);
        reviewDetailDTO.setBookScore(review.getBookScore());
        reviewDetailDTO.setIsbn(review.getBook().getIsbn());
        reviewDetailDTO.setContent(review.getContent());
        reviewDetailDTO.setTitle(review.getTitle());
        reviewDetailDTO.setIsSecret(review.getIsSecret());


        reviewDetailDTO.setLikeCount(reviewLikeRepository.countReviewLikeByReview(review));
        reviewDetailDTO.setPhotoUrlList(reviewPhotoRepository.findAllByReviewId(reviewId));
        reviewDetailDTO.setReviewCommentCount(reviewCommentRepository.countReviewCommentByReview(review));

        List<ReviewCommentDTO> reviewCommentDTOList = new ArrayList<>();
        for (ReviewComment reviewComment:reviewCommentRepository.findAllByReview(review).orElseThrow()) {
            ReviewCommentDTO reviewCommentDTO = new ReviewCommentDTO();
            reviewCommentDTO.setCommentId(reviewComment.getId());
            reviewCommentDTO.setUserId(reviewComment.getUser().getId());
            reviewCommentDTO.setContent(reviewComment.getContents());
            reviewCommentDTO.setUserImagePath((reviewComment.getUser().getImagePath()));
            reviewCommentDTO.setDepth(reviewComment.getDepth());
            reviewCommentDTO.setParentId(reviewComment.getParent().getId());

            reviewCommentDTOList.add(reviewCommentDTO);
        }
        reviewDetailDTO.setReviewCommentList(reviewCommentDTOList);
        List<String> HashtagList = new ArrayList<>();
        for(ReviewHashtag reviewHashtag : reviewHashtagRepository.findAllByReviewId(reviewId)){
            HashtagList.add(reviewHashtag.getContent());
        }
        reviewDetailDTO.setReviewHashtagList(HashtagList);

        return reviewDetailDTO;
    }

    @Override
    public Long registComment(ReviewCommentRequest reviewCommentRequest){
        ReviewComment reviewComment =ReviewComment.registReviewComment().
                reviewCommentRequest(reviewCommentRequest).
                user(userRepository.findById(reviewCommentRequest.getUserId()).orElseThrow()).
                review(reviewRepository.findById(reviewCommentRequest.getReviewId()).orElseThrow()).
                parent(reviewCommentRepository.findById(reviewCommentRequest.getParentId()).orElseThrow()).build();

        ReviewComment savedReviewComment = reviewCommentRepository.save(reviewComment);

        return savedReviewComment.getId();
    }

    @Override
    public List<RandomReviewDTO> showRandomFeeds(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        // 총 30개
        // 약 내가 보고싶은 책과 관련된 리뷰 20개, 리뷰가 많은 책 10개
        // 책당 리뷰는 4개씩
        List<RandomReviewDTO> list = new ArrayList<>();
        List<RandomReviewDTO> wishlist = getWishReviewList(user);
        list.addAll(wishlist);
        List<RandomReviewDTO> hotlist = getHotReviewList(30-wishlist.size());
        list.addAll(hotlist);

        // TODO: 2022-09-22 정렬방법에 의해 리뷰 순서가 섞임 
        HashSet<RandomReviewDTO> listset = new HashSet<>(list);
        return new ArrayList<>(listset);
    }

    public List<RandomReviewDTO> getWishReviewList(User user){

        List<RandomReviewDTO> wishlist = new ArrayList<>();
        List<Library> wishLibraries = libraryRepository.findAllByUserAndLibraryType(user, LibraryType.WISH);
        for (Library library: wishLibraries) {
            List<Review> tmp = reviewRepository.findTop4ByBookOrderByIdDesc(library.getBook());
            for (Review review: tmp) {
                wishlist.add(RandomReviewDTO.createRandomReview().
                        review(review).
                        likeCount(reviewLikeRepository.countReviewLikeByReview(review)).
                        reviewPhotos(reviewPhotoRepository.findAllByReviewId(review.getId())).
                        build());
                if(wishlist.size()>=20) return wishlist;
            }
        }
        return wishlist;
    }

    public List<RandomReviewDTO> getHotReviewList(int size){
        List<RandomReviewDTO> hotList = new ArrayList<>();
        // TODO: 2022-09-22 리뷰별 갯수 제한
        List<Review> reviewList = reviewRepository.findByBook_IsbnIn(reviewRepository.findHotBooks());

        for (Review review: reviewList) {
            hotList.add(RandomReviewDTO.createRandomReview().
                    review(review).
                    likeCount(reviewLikeRepository.countReviewLikeByReview(review)).
                    reviewPhotos(reviewPhotoRepository.findAllByReviewId(review.getId())).
                    build());
            if(hotList.size()>=size) return hotList;
        }

        return hotList;
    }
}
