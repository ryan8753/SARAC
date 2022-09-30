package com.sarac.sarac.review.payload.response;

import com.sarac.sarac.review.entity.ReviewComment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCommentDTO {

    private Long commentId;
    private Long userId;
    private Long kakaoId;

    private String userNickname;

    private String content;

    private String userImagePath;

    private Integer depth;

    private Long parentId;


    @Builder(builderMethodName = "createReviewCommentDTO")
    ReviewCommentDTO(ReviewComment reviewComment){
        this.commentId=reviewComment.getId();
        this.userId=reviewComment.getUser().getId();
        this.kakaoId=reviewComment.getUser().getKakaoId();
        this.userNickname=reviewComment.getUser().getNickname();
        this.content=reviewComment.getContents();
        this.userImagePath=reviewComment.getUser().getImagePath();
        this.depth= reviewComment.getDepth();
        if(reviewComment.getParent()==null){
            this.parentId=0L;
        }else {
            this.parentId=reviewComment.getParent().getId();
        }
    }
}
