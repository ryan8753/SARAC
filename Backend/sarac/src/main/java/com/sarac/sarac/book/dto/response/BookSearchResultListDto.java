package com.sarac.sarac.book.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BookSearchResultListDto {
    private List<BookSearchResultDto> results;
    private int totalPages;
    private int currentPage;
    private boolean bestBook;
}
