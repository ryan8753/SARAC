package com.sarac.sarac.cafe.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class CafeLikeResponse {

    @Getter
    public static class GoodBadCounts {
        private long goods;
        private long bads;

        @Builder
        public GoodBadCounts(long goods, long bads) {
            this.goods = goods;
            this.bads = bads;
        }
    }
}
