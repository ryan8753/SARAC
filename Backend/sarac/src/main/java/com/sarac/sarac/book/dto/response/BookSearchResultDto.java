package com.sarac.sarac.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BookSearchResultDto {
    private String isbn;
    private String thumbnail;
    private String title;
    private String author;
    private double score;
}