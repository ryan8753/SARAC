package com.sarac.sarac.book.entity;

import com.sarac.sarac.book.util.dto.AladinAuthorDto;
import com.sarac.sarac.book.util.dto.AladinDto;
import com.sarac.sarac.book.util.dto.AladinItemDto;
import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.review.entity.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
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

    @Column(length = 2000)
    private String toc;

    @ColumnDefault("0")
    private int price;

    @ColumnDefault("0")
    private int page;

    @Column(length = 100)
    private String genre;

    @Column(length = 100, nullable = true)
    private String vol;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Library> libraries = new HashSet<>();

    public void updateDetail(AladinItemDto aladinItemDto) {
        if(isContentEmpty(this.bookImgUrl))
            this.bookImgUrl = aladinItemDto.getCover();

        if(isContentEmpty(this.publisher))
            this.publisher = aladinItemDto.getPublisher();

        if(this.price == 0)
            this.price = aladinItemDto.getPriceStandard();

        this.description = aladinItemDto.getDescription();
        this.bookTitle = aladinItemDto.getTitle();
        this.genre = aladinItemDto.getCategoryName();
        this.page = aladinItemDto.getBookinfo().getItemPage();
        this.toc = aladinItemDto.getBookinfo().getToc()
                .replaceAll("\n", "").replaceAll("<BR>", "<br>");

        StringBuilder sb = new StringBuilder();

        for(AladinAuthorDto aladinAuthorDto : aladinItemDto.getBookinfo().getAuthors()) {
            if(AuthorType.AUTHOR.name().equalsIgnoreCase(aladinAuthorDto.getAuthorType())) {
                sb.append(aladinAuthorDto.getName()).append(", ");
            }
        }
        this.author = sb.substring(0, sb.length() - 2).toString();
    }

    private boolean isContentEmpty(String content) {
        return (content == null) || (content.trim().length() == 0);
    }
}
