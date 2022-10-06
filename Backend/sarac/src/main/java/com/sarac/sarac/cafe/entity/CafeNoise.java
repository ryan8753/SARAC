package com.sarac.sarac.cafe.entity;

import com.sarac.sarac.baseEntitiy.BaseEntity;
import com.sarac.sarac.user.entitiy.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CafeNoise extends BaseEntity {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double noise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", referencedColumnName = "id")
    private Cafe cafe;

    @Builder
    public CafeNoise(double noise, User user, Cafe cafe) {
        this.noise = noise;
        this.user = user;
        this.cafe = cafe;
    }
}
