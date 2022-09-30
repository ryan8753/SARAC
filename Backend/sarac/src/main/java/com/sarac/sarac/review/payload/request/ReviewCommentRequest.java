package com.sarac.sarac.review.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCommentRequest {

    private Long reviewId;

    private String contents;

    private Integer depth;

    private Long parentId;
}
