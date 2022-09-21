package com.sarac.sarac.review.payload.response;

import com.sarac.sarac.review.entity.ReviewHashtag;
import com.sarac.sarac.review.entity.ReviewPhoto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewDetailDTO {

    private Long id;

    private String isbn;

    private String title;

    private String content;

    private Integer bookScore;

    private Boolean isSecret;

    private Integer likeCount;

    private List<ReviewPhoto> photoUrlList;

    private Integer reviewCommentCount;

    private List<ReviewCommentDTO> reviewCommentList;

    private List<String> reviewHashtagList;
}
