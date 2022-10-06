package com.sarac.sarac.cafe.dto.request;

import com.sarac.sarac.cafe.entity.Cafe;
import com.sarac.sarac.user.entitiy.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class CafeLikeRequest {

    // 카페에 좋아요/싫어요를 누른 상태가 변하는 경우
    @Getter
    @RequiredArgsConstructor
    @ToString
    public static class CafeLikeInfo {
        private Cafe cafe;
        private User user;
        private boolean goodBad;

        @Builder
        public CafeLikeInfo(Cafe cafe, User user, boolean goodBad) {
            this.cafe = cafe;
            this.user = user;
            this.goodBad = goodBad;
        }
    }

    // 카페에 좋아요를 눌렀는지 확인
    @Getter
    @RequiredArgsConstructor
    @ToString
    public static class CafeLikeStatus {
        private Cafe cafe;
        private User user;

        @Builder
        public CafeLikeStatus(Cafe cafe, User user) {
            this.cafe = cafe;
            this.user = user;
        }
    }
}
