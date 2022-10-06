package com.sarac.sarac.cafe.entity;

import com.sarac.sarac.user.entitiy.User;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CafeLike {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "goodBad")
    private boolean goodBad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", referencedColumnName = "id")
    private Cafe cafe;

    @Builder
    public CafeLike(User user, Cafe cafe, boolean goodBad) {
        this.user = user;
        this.cafe = cafe;
        this.goodBad = goodBad;
    }
}
