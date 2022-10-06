package com.sarac.sarac.book.controller;

import com.sarac.sarac.book.dto.response.BookSearchResultListDto;
import com.sarac.sarac.book.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/book/search")
public class BookSearchController {

    private final BookSearchService bookSearchService;

    @GetMapping
    public ResponseEntity<BookSearchResultListDto> getBookSearchResult(String keyword, String type, @PageableDefault(sort = "isbn", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().body(bookSearchService.getSearchResult(keyword, type, pageable));
    }

    @GetMapping("/best")
    public ResponseEntity<?> getBestBooks() {
        return ResponseEntity.ok().body(bookSearchService.getBestBooks());
    }
}
