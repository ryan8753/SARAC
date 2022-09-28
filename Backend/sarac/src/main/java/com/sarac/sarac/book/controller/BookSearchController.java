package com.sarac.sarac.book.controller;

import com.sarac.sarac.book.dto.response.BookSearchResultListDto;
import com.sarac.sarac.book.service.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<BookSearchResultListDto> getBookSearchResult(String keyword) {
        return ResponseEntity.ok().body(bookSearchService.getSearchResult(keyword));
    }

    @GetMapping("/best")
    public ResponseEntity<?> getBestBooks() {
        return ResponseEntity.ok().body(bookSearchService.getBestBooks());
    }
}
