package com.sarac.sarac.review.payload.response;

import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.entity.ReviewHashtag;
import com.sarac.sarac.review.entity.ReviewPhoto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewDetailDTO {

    private Long reviewId;

    private Long authorKakaoId;

    private String isbn;

    private boolean isLike;

    private String title;

    private String bookImgUrl;

    private String bookTitle;

    private String content;

    private Integer bookScore;

    private Boolean isSecret;

    private Integer likeCount;

    private List<String> photoUrl;

    private Integer reviewCommentCount;

    private List<ReviewCommentDTO> reviewCommentList;

    private List<String> reviewHashtagList;

    @Builder(builderMethodName = "createReviewDetailDTO")
    ReviewDetailDTO(Review review, boolean isLike, Integer likeCount, List<String>  photoUrl, Integer reviewCommentCount, List<ReviewCommentDTO> reviewCommentList,List<String> HashtagList){
        this.reviewId =review.getId();
        this.isLike=isLike;
        this.authorKakaoId=review.getUser().getKakaoId();
        this.bookImgUrl=review.getBook().getBookImgUrl();
        this.bookTitle=review.getBook().getBookTitle();
        this.bookScore=review.getBookScore();
        this.content=review.getContent();
        this.title=review.getTitle();
        this.isSecret=review.getIsSecret();
        this.isbn=review.getBook().getIsbn();
        this.likeCount=likeCount;
        this.photoUrl=photoUrl;
        this.reviewCommentCount=reviewCommentCount;
        this.reviewCommentList=reviewCommentList;
        this.reviewHashtagList=HashtagList;
    }
}
