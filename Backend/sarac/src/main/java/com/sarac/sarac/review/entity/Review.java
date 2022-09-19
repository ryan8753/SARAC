package com.sarac.sarac.review.entity;

import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.user.entitiy.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "writer", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "isbn", referencedColumnName = "isbn")
    private Book book;

    @Column(length = 100)
    private String title;

    @Column(length = 2000)
    private String content;

    private Integer bookScore;

    private Boolean isSecret;

}
