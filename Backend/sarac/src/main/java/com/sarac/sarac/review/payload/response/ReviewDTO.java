package com.sarac.sarac.review.payload.response;

import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.user.entitiy.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class ReviewDTO {


    private String isbn;

    private String title;

    private String content;

    private Integer bookScore;

    private Boolean isSecret;

}
