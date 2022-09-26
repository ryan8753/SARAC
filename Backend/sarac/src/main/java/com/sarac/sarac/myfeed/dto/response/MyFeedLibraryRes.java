package com.sarac.sarac.myfeed.dto.response;

import com.sarac.sarac.library.type.LibraryType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MyFeedLibraryRes {

    private String isbn;
    private String bookImgUrl;
    private String bookTitle;
    private String author;
    private LibraryType libraryType;
}
