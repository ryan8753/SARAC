package com.sarac.sarac.review.payload.response;

import com.sarac.sarac.review.entity.ReviewPhoto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ReviewListDTO {
    private Long reviewId;

    private String bookTitle;

    private String title;

    private List<ReviewPhoto> photoUrlList;

}
