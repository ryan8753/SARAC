package com.sarac.sarac.cafe.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
public class CafeNoiseResponse {

    @Getter
    public static class NoiseInfo {
        private LocalDateTime time;
        private double noise;

        @Builder
        public NoiseInfo(LocalDateTime time, double noise) {
            this.time = time;
            this.noise = noise;
        }
    }
}
