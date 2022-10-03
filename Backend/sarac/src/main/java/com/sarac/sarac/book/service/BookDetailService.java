package com.sarac.sarac.book.service;

import com.sarac.sarac.book.entity.dto.response.request.BookDetailRequest;
import com.sarac.sarac.book.dto.response.BookInfoDto;

public interface BookDetailService {

    BookInfoDto showBookInfo(long userId, String isbn);



    void updateLibraryType(BookDetailRequest bookDetailRequest);

    void deleteLibraryType(long userId, String isbn);


}
