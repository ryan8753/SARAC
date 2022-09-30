package com.sarac.sarac.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BookSearchResultDto {
    private String isbn;
    private String bookImgUrl;
    private String bookTitle;
    private String author;
    private double bookScore;
}