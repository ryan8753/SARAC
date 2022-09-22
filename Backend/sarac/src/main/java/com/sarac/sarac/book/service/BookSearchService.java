package com.sarac.sarac.book.service;

import com.sarac.sarac.book.dto.response.BookSearchResultDto;
import com.sarac.sarac.book.dto.response.BookSearchResultListDto;
import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.book.repository.BookSearchRepository;
import com.sarac.sarac.review.entity.BookScore;
import com.sarac.sarac.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookSearchService {

    private final BookSearchRepository bookSearchRepository;

    private final ReviewRepository reviewRepository;

    private final int EMPTY = 0;

    public BookSearchResultListDto getSearchResult(String keyword) {
        List<Book> bookList
                = bookSearchRepository.findByIsbnOrAuthorContainsOrBookTitleContains(keyword, keyword, keyword);

        List<BookSearchResultDto> bookSearchResultDtoList = bookList.stream()
                .map(book -> BookSearchResultDto.builder()
                            .isbn(book.getIsbn())
                            .author(book.getAuthor())
                            .title(book.getBookTitle())
                            .thumbnail(book.getBookImgUrl())
                            .score(reviewRepository.existsByBook(book) ?
                                    reviewRepository.findAllByBook(book).stream()
                                                .mapToInt(BookScore::getBookScore).average().getAsDouble() : EMPTY)
                            .build()
                ).collect(Collectors.toList());

        return BookSearchResultListDto.builder()
                .results(bookSearchResultDtoList)
                .build();
    }

}
