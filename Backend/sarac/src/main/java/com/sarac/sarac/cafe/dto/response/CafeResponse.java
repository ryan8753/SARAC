package com.sarac.sarac.cafe.dto.response;

import com.sarac.sarac.cafe.entity.Cafe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
public class CafeResponse {

    @Getter
    public static class CafeListInMapArea {
        private List<Cafe> cafeList;

        @Builder
        public CafeListInMapArea(List<Cafe> cafeList) {
            this.cafeList = cafeList;
        }
    }

    @Getter
    public static class CafeInfo {
        private Cafe cafe;

        @Builder
        public CafeInfo(Cafe cafe) {
            this.cafe = cafe;
        }
    }
}
