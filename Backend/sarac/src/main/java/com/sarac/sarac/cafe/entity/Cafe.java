package com.sarac.sarac.cafe.entity;

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
public class Cafe {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String bookImgUrl;

    private Double latitude;

    private Double longitude;

    @Column(length = 20)
    private String cafeName;



    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<CafeLike> cafeLikes = new HashSet<>();

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<CafeNoise> cafeNoises = new HashSet<>();

}
