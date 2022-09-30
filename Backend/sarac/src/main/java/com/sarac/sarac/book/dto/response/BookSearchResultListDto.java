package com.sarac.sarac.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class BookSearchResultListDto {
    List<BookSearchResultDto> results;
}
