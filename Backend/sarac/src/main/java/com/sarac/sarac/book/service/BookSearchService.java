package com.sarac.sarac.book.service;

import com.sarac.sarac.book.dto.request.SearchType;
import com.sarac.sarac.book.dto.response.BookSearchResultDto;
import com.sarac.sarac.book.dto.response.BookSearchResultListDto;
import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.book.repository.BookSearchRepository;
import com.sarac.sarac.review.entity.BookScore;
import com.sarac.sarac.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookSearchService {

    private final BookSearchRepository bookSearchRepository;

    private final ReviewRepository reviewRepository;

    private final int EMPTY = 0;

    private final int START_BOOK_INDEX = 0;

    private final int MAX_BEST_BOOK_COUNT = 30;

    public BookSearchResultListDto getSearchResult(String keyword, String type, Pageable pageable) {
        Page<Book> bookListWithPage =
                SearchType.KEYWORD.name().equalsIgnoreCase(type) ?
                        bookSearchRepository.findByDescriptionContains(keyword, pageable)
                        : bookSearchRepository.findByIsbnOrAuthorContainsOrBookTitleContains(keyword, keyword, keyword, pageable);

        return BookSearchResultListDto.builder()
                .results(calcBookScores(bookListWithPage.getContent()))
                .totalElements(bookListWithPage.getTotalElements())
                .currentPage(bookListWithPage.getNumber())
                .bestBook(false)
                .build();
    }

    public BookSearchResultListDto getBestBooks() {

        List<Book> bookList = bookSearchRepository.findBestBooks();

        return BookSearchResultListDto.builder()
                .results(calcBookScores(bookList.subList(START_BOOK_INDEX, cutListCount(bookList))))
                .bestBook(true)
                .build();
    }

    private List<BookSearchResultDto> calcBookScores(List<Book> bookList) {
        return bookList.stream()
                .map(book -> BookSearchResultDto.builder()
                        .isbn(book.getIsbn())
                        .author(book.getAuthor())
                        .bookTitle(book.getBookTitle())
                        .bookImgUrl(book.getBookImgUrl())
                        .bookScore(reviewRepository.existsByBook(book) ?
                                reviewRepository.findAllByBook(book).stream()
                                        .mapToInt(BookScore::getBookScore).average().getAsDouble() : EMPTY)
                        .build())
                .collect(Collectors.toList());
    }

    private int cutListCount(List<Book> bookList) {
        return bookList.size() > MAX_BEST_BOOK_COUNT ? MAX_BEST_BOOK_COUNT : bookList.size();
    }

}
