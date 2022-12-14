package com.sarac.sarac.library.entity;

import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.library.type.LibraryType;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Library {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LibraryType libraryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn", referencedColumnName = "isbn")
    private Book book;

    @Builder
    public Library(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public void insertLibraryType(LibraryType libraryType) {
        this.libraryType = libraryType;
    }
}
