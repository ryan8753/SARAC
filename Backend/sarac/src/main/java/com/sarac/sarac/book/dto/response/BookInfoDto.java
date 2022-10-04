package com.sarac.sarac.book.dto.response;

import com.sarac.sarac.library.type.LibraryType;
import lombok.*;

@Builder
@Getter
public class BookInfoDto {

    private String bookTitle;

    private String bookImgUrl;

    private String isbn;

    private String author;

    private LibraryType libraryType;

    private Double bookScore;

    private String publisher;

    private String description;

    private int price;

    private String vol;

    private String genre;

    private String toc;

    private int page;
}
