package com.sarac.sarac.review.entity;

import com.sarac.sarac.review.payload.request.ReviewCommentRequest;
import com.sarac.sarac.user.entitiy.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ReviewComment {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String contents;

    private Integer depth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ReviewComment parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
    private List<ReviewComment> children;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Review review;


    @Builder(builderMethodName = "registReviewComment")
    ReviewComment(ReviewCommentRequest reviewCommentRequest, User user, Review review, ReviewComment parent){
        this.review =review;
        this.user=user;
        this.contents = reviewCommentRequest.getContents();
        this.depth= reviewCommentRequest.getDepth();;
        this.parent=parent;
    }

}
