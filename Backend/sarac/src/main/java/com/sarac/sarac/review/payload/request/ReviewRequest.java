package com.sarac.sarac.review.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ReviewRequest {

    private  Long id;

    private String isbn;

    private Long writer;

    private String title;

    private String content;

    private Integer bookScore;

    private Boolean isSecret;

    private Set<String> hashtag;




}
