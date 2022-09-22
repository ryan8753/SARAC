package com.sarac.sarac.book.entity;

import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.review.entity.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book {

    @Id
    @Column(unique = true, length = 13)
    private String isbn;

    @Column(length=1000)
    private String bookImgUrl;

    @Column(length = 1200)
    private String bookTitle;

    @Column(length = 1000)
    private String author;

    @Column(length = 1000)
    private String publisher;

    @Column(length = 2000)
    private String description;

    private int price;

    private int page;

    @Column(length = 20)
    private String genre;

    @Column(length = 20)
    private String vol;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Library> libraries = new HashSet<>();

}
