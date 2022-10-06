package com.sarac.sarac.cafe.dto.request;

import com.sarac.sarac.cafe.entity.Cafe;
import com.sarac.sarac.user.entitiy.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

public class CafeNoiseRequest {

    @Getter
    @RequiredArgsConstructor
    @ToString
    public static class CafeNoiseInfo {
        private Cafe cafe;
        private User user;
        private double noise;

        @Builder
        public CafeNoiseInfo(Cafe cafe, User user, double noise) {
            this.cafe = cafe;
            this.user = user;
            this.noise = noise;
        }
    }
}
