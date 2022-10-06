package com.sarac.sarac.cafe.exception;

public class CafeException {

    public static class CafeNotFoundException extends RuntimeException {
        public CafeNotFoundException() {
            super("해당하는 카페가 존재하지 않습니다.");
        }
    }
}
