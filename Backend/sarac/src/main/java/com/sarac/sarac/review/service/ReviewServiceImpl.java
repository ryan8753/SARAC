package com.sarac.sarac.review.service;

import com.sarac.sarac.book.repository.BookRepository;
import com.sarac.sarac.global.util.FileUpload;
import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.library.repository.LibraryRepository;
import com.sarac.sarac.library.type.LibraryType;
import com.sarac.sarac.review.entity.*;
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
import java.util.*;
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
    @Transactional
    public Long registerReview(ReviewRequest review) {

        Review saveReview = new Review();
        saveReview.setBook(bookRepository.findOneByIsbn(review.getIsbn())
                .orElseThrow(IllegalArgumentException::new));
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
            originReview.setBook(bookRepository.findOneByIsbn(review.getIsbn())
                    .orElseThrow(IllegalArgumentException::new));
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

        List<Review> ReviewList = reviewRepository.findAllByBookIsbnAndIsSecret(isbn, false); // 공개리뷰만 가져오도록 변경
        List<ReviewListDTO> reviewListDTO = new ArrayList<>();
        for (Review review : ReviewList) {
            List<ReviewPhoto> reviewPhotoList = reviewPhotoRepository.findAllByReviewId(review.getId());
            List<String> photoUrlList = new ArrayList<>();
            photoUrlList.addAll(ifUrlIsEmpty(reviewPhotoList, review));
            
            reviewListDTO.add(
                    ReviewListDTO.builder()
                            .bookTitle(review.getBook().getBookTitle())
                            .title(review.getTitle())
                            .reviewId(review.getId())
                            .photoUrlList(photoUrlList)
                            .build());
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
    public ReviewDetailDTO showDetailReview(long reviewId, Map<String, Object> token) {

        User user = userRepository.findOneByKakaoId(
                (Long)jwtUtil.parseJwtToken((String) token.get("authorization")).get("id"));
        Review review = reviewRepository.findById(reviewId).orElseThrow();

        List<ReviewCommentDTO> reviewCommentDTOList = new ArrayList<>();
        for (ReviewComment reviewComment:reviewCommentRepository.findAllByReview(review).orElseThrow()) {
            reviewCommentDTOList.add(
                    ReviewCommentDTO.createReviewCommentDTO().
                    reviewComment(reviewComment).
                    build()
            );
        }

        List<String> HashtagList = new ArrayList<>();
        for(ReviewHashtag reviewHashtag : reviewHashtagRepository.findAllByReviewId(reviewId)){
            HashtagList.add(reviewHashtag.getContent());
        }

        return ReviewDetailDTO.createReviewDetailDTO()
                .review(review)
                .likeCount(reviewLikeRepository.countReviewLikeByReview(review))
                .photoUrl(ifUrlIsEmpty(reviewPhotoRepository.findAllByReviewId(reviewId),review))
                .reviewCommentCount(reviewCommentRepository.countReviewCommentByReview(review))
                .isLike(reviewLikeRepository.existsByUserAndReview(user,review))
                .reviewCommentList(reviewCommentDTOList)
                .HashtagList(HashtagList)
                .build();
    }

    @Override
    public Long registComment(ReviewCommentRequest reviewCommentRequest, Map<String, Object> token){
        User user = userRepository.findOneByKakaoId(
                (Long)jwtUtil.parseJwtToken((String) token.get("authorization")).get("id"));
        ReviewComment reviewComment =ReviewComment.registReviewComment().
                reviewCommentRequest(reviewCommentRequest).
                user(user).
                review(reviewRepository.findById(reviewCommentRequest.getReviewId()).orElseThrow()).
                parent(reviewCommentRepository.findById(reviewCommentRequest.getParentId()).orElse(null)).build();

        ReviewComment savedReviewComment = reviewCommentRepository.save(reviewComment);

        return savedReviewComment.getId();
    }

    @Override
    public void deleteComment(Long commentId){
        ReviewComment reviewComment= reviewCommentRepository.findById(commentId).orElseThrow();
        // 댓글인경우 + 답글이 달린경우
        if(reviewComment.getDepth()==0 && reviewComment.getChildren().size()!=0){
            //내용을 "deleted"로 변경
            reviewComment.deleteComment();
            reviewCommentRepository.save(reviewComment);

        //답글인경우
        }else{
            reviewCommentRepository.delete(reviewComment);
        }
   }

    @Override
    public List<RandomReviewDTO> showRandomFeeds(Map<String, Object> token) {
        User user = userRepository.findOneByKakaoId(
                (Long)jwtUtil.parseJwtToken((String) token.get("authorization")).get("id"));
        // 총 30개
        // 약 내가 보고싶은 책과 관련된 리뷰 20개, 리뷰가 많은 책 10개
        // 책당 리뷰는 4개씩
        List<RandomReviewDTO> list = new ArrayList<>();
        List<RandomReviewDTO> wishlist = getWishReviewList(user);
        list.addAll(wishlist);
        List<RandomReviewDTO> hotlist = getHotReviewList(30-wishlist.size(), user);
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
                        reviewPhotos(ifUrlIsEmpty(reviewPhotoRepository.findAllByReviewId(review.getId()), review)).
                        isLike(reviewLikeRepository.existsByUserAndReview(user, review)).
                        build());
                if(wishlist.size()>=20) return wishlist;
            }
        }
        return wishlist;
    }

    public List<RandomReviewDTO> getHotReviewList(int size, User user){
        List<RandomReviewDTO> hotList = new ArrayList<>();
        // TODO: 2022-09-22 리뷰별 갯수 제한
        List<Review> reviewList = reviewRepository.findByBook_IsbnIn(reviewRepository.findHotBooks());


        for (Review review: reviewList) {
            hotList.add(RandomReviewDTO.createRandomReview().
                    review(review).
                    likeCount(reviewLikeRepository.countReviewLikeByReview(review)).
                    reviewPhotos(ifUrlIsEmpty(reviewPhotoRepository.findAllByReviewId(review.getId()), review)).
                    isLike(reviewLikeRepository.existsByUserAndReview(user, review)).
                    build());
            if(hotList.size()>=size) return hotList;
        }

        return hotList;
    }

    public List<String> convertReviewPhotoListtoUrlList(List<ReviewPhoto> reviewPhoto){
        return reviewPhoto.stream().map(ReviewPhoto::getPhotoUrl).collect(Collectors.toList());
    }

    public List<String> ifUrlIsEmpty(List<ReviewPhoto> reviewPhotos, Review review){
        if(reviewPhotos.size() == 0) {
            // 책 썸네일 대신 보내줌 이거는 Stirng이라 리스트로만 감싸도 되지않을까요?
            List<String> tmp = new ArrayList<>();
            tmp.add(review.getBook().getBookImgUrl());
            return tmp;
        }
        // 등록된 사진 있을 경우
        else {
            // url만 뽑아내서 보내줌
            return convertReviewPhotoListtoUrlList(reviewPhotos);
        }
    }

    public void toggleReviewLike(Map<String, Object> token, Long reivewId){
        User user = userRepository.findOneByKakaoId(
                (Long)jwtUtil.parseJwtToken((String) token.get("authorization")).get("id"));

        Review review = reviewRepository.findById(reivewId).orElseThrow();

        // 있다면 삭제
        if(reviewLikeRepository.existsByUserAndReview(user,review)){
            reviewLikeRepository.delete(reviewLikeRepository.findByUserAndReview(user,review));
        }else {
            // 없다면 생성
            reviewLikeRepository.save(
                    ReviewLike.createReviewLike()
                            .user(user)
                            .review(review)
                            .build()
            );
        }

    }
}
