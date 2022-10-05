package com.sarac.sarac.book.service;

import com.sarac.sarac.book.dto.request.BookDetailRequest;
import com.sarac.sarac.book.dto.response.BookInfoDto;
import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.book.repository.BookRepository;
import com.sarac.sarac.book.util.AladinUtil;
import com.sarac.sarac.book.util.dto.AladinDto;
import com.sarac.sarac.book.util.dto.AladinResponse;
import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.library.repository.LibraryRepository;
import com.sarac.sarac.review.entity.BookScore;
import com.sarac.sarac.review.repository.ReviewRepository;
import com.sarac.sarac.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookDetailServiceImpl implements BookDetailService {

    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    private final AladinUtil aladinUtil;

    @Override
    public BookInfoDto showBookInfo(long userId, String isbn) {

        Book book = bookRepository.findOneByIsbn(isbn)
                .orElseThrow(IllegalArgumentException::new);

        Library library = libraryRepository.findByUserIdAndBookIsbn(userId, isbn)
                .orElseGet(Library::new);

        checkBookDetailSave(isbn, book);

        return BookInfoDto.builder()
                .bookTitle(book.getBookTitle())
                .bookImgUrl(book.getBookImgUrl())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .bookScore(reviewRepository.existsByBook(book) ?
                            reviewRepository.findAllByBook(book).stream().mapToInt(BookScore::getBookScore).average().getAsDouble() : 0)
                .price(book.getPrice())
                .publisher(book.getPublisher())
                .description(book.getDescription())
                .vol(book.getVol())
                .libraryType(library.getLibraryType())
                .genre(book.getGenre())
                .toc(book.getToc())
                .page(book.getPage())
                .build();
    }


    @Transactional
    @Override
    public void updateLibraryType(BookDetailRequest bookDetailRequest) {

        Library library = libraryRepository.findByUserIdAndBookIsbn(bookDetailRequest.getUserId(), bookDetailRequest.getIsbn())
                .orElseGet(() -> Library.builder()
                                .book(bookRepository.findOneByIsbn(bookDetailRequest.getIsbn())
                                        .orElseThrow(IllegalArgumentException::new))
                                .user(userRepository.findOneById(bookDetailRequest.getUserId()))
                                .build());

        library.insertLibraryType(bookDetailRequest.getLibraryType());

        libraryRepository.save(library);
    }

    @Transactional
    @Override
    public void deleteLibraryType(long userId, String isbn) {
        libraryRepository.deleteByUserIdAndBookIsbn(userId, isbn);
    }

    private void checkBookDetailSave(String isbn, Book book) {
        AladinResponse aladinResponse = aladinUtil.getBookDetailData(isbn);

        if(canBookSaveDetail(aladinResponse, book))
            saveDetailBookData((AladinDto) aladinResponse, book);
    }

    @Transactional
    private void saveDetailBookData(AladinDto aladinDto, Book book) {
        book.updateDetail(aladinDto.getItem().get(0));
        bookRepository.save(book);
    }

    private boolean canBookSaveDetail(AladinResponse aladinResponse, Book book) {
        return (aladinResponse instanceof AladinDto) && (book.getGenre() == null || book.getGenre().trim().length() == 0);
    }
}
