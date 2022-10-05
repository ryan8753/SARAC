package com.sarac.sarac.review.payload.response;

import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.entity.ReviewComment;
import com.sarac.sarac.review.entity.ReviewPhoto;
import com.sarac.sarac.review.payload.request.ReviewCommentRequest;
import com.sarac.sarac.user.entitiy.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class RandomReviewDTO {

    private Long reviewId;

    private String isbn;

    private String bookImgUrl;

    private String title;

    private boolean isLike;

    private String bookTitle;

    private String content;

    private Integer bookScore;

    private Integer likeCount;

    private List<String> photoUrl;

    @Builder(builderMethodName = "createRandomReview")
    RandomReviewDTO(Review review, Integer likeCount, List<String> reviewPhotos, boolean isLike){
        this.reviewId =review.getId();
        this.isbn=review.getBook().getIsbn();
        this.bookImgUrl=review.getBook().getBookImgUrl();
        this.title=review.getTitle();
        this.isLike=isLike;
        this.bookTitle = review.getBook().getBookTitle();
        this.content=review.getContent();
        this.bookScore=review.getBookScore();
        this.likeCount=likeCount;
        this.photoUrl = reviewPhotos;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof RandomReviewDTO)) {
            return false;
        }

        RandomReviewDTO r = (RandomReviewDTO) o;
        return r.reviewId.equals(this.reviewId);
    }

    @Override
    public int hashCode(){
        return (this.reviewId.hashCode());
    }
}
