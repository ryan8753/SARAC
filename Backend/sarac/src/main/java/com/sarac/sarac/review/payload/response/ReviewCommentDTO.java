package com.sarac.sarac.review.payload.response;

import com.sarac.sarac.review.entity.ReviewComment;
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


}
