package com.sarac.sarac.cafe.exception;

public class CafeLikeException {

    public static class NoSuchCafeLikeException extends RuntimeException {
        public NoSuchCafeLikeException() {
            super("해당 좋아요 정보가 존재하지 않습니다.");
        }
    }
}
