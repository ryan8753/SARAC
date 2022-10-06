package com.sarac.sarac.cafe.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class CafeRequest {

    @Getter
    @RequiredArgsConstructor
    @ToString
    public static class CafeListMapArea {
        private double minLatitude;
        private double maxLatitude;
        private double minLongitude;
        private double maxLongitude;

        @Builder
        public CafeListMapArea(double minLatitude, double maxLatitude, double minLongitude, double maxLongitude) {
            this.minLatitude = minLatitude;
            this.maxLatitude = maxLatitude;
            this.minLongitude = minLongitude;
            this.maxLongitude = maxLongitude;
        }
    }
}
