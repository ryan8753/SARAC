package com.sarac.sarac.review.payload.response;

import com.sarac.sarac.review.entity.ReviewPhoto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewListDTO {
    private Long reviewId;

    private String bookTitle;

    private String title;

    private List<ReviewPhoto> photoUrlList;

}
