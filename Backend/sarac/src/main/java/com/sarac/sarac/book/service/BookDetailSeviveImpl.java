package com.sarac.sarac.book.service;

import com.sarac.sarac.book.entity.dto.response.request.BookDetailRequest;
import com.sarac.sarac.book.dto.response.BookInfoDto;
import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.book.repository.BookRepository;
import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.library.repository.LibraryRepository;
import com.sarac.sarac.library.type.LibraryType;
import com.sarac.sarac.review.entity.BookScore;
import com.sarac.sarac.review.repository.ReviewRepository;
import com.sarac.sarac.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDetailSeviveImpl implements BookDetailService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public BookInfoDto showBookInfo(long userId, String isbn) {

        Book book = bookRepository.findOneByIsbn(isbn);
        Library library = libraryRepository.findByUserIdAndBookIsbn(userId,isbn);
        LibraryType libraryType = null;
        if(library!=null){
            libraryType = library.getLibraryType();
        }

        BookInfoDto bookInfoDto = BookInfoDto.builder()
                .bookTitle(book.getBookTitle())
                .bookImgUrl(book.getBookImgUrl())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .bookScore(reviewRepository.existsByBook(book) ?
                        reviewRepository.findAllByBook(book).stream()
                                .mapToInt(BookScore::getBookScore).average().getAsDouble() : 0)
                .price(book.getPrice())
                .publisher(book.getPublisher())
                .description(book.getDescription())
                .vol(book.getVol())
                .libraryType(libraryType)
                .build();
        return bookInfoDto;
    }


    @Override
    public void updateLibraryType(BookDetailRequest bookDetailRequest) {

        Library library = libraryRepository.findByUserIdAndBookIsbn(bookDetailRequest.getUserId(), bookDetailRequest.getIsbn());
        if(library!=null){
            library.setLibraryType(bookDetailRequest.getLibraryType());
            libraryRepository.save(library);
        }else {

            Library newlibrary = Library.builder()
                    .book(bookRepository.findOneByIsbn(bookDetailRequest.getIsbn()))
                    .libraryType(bookDetailRequest.getLibraryType())
                    .user(userRepository.findOneById(bookDetailRequest.getUserId()))
                    .build();

            libraryRepository.save(newlibrary);
        }



    }

    @Override
    public void deleteLibraryType(long userId, String isbn) {
        libraryRepository.deleteByUserIdAndBookIsbn(userId,isbn);
    }
}
