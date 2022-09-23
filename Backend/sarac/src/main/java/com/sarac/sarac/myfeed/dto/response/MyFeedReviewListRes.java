package com.sarac.sarac.myfeed.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyFeedReviewListRes {
    private Long reviewId;

    private String bookTitle;

    private String title;

    private List<String> photoUrlList;

    private Boolean isSecret;
}
