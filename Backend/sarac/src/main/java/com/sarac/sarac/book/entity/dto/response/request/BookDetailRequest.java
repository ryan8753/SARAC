package com.sarac.sarac.book.entity.dto.response.request;

import com.sarac.sarac.library.type.LibraryType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDetailRequest {

    private String isbn;

    private Long userId;

    private LibraryType libraryType;
}
