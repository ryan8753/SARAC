package com.sarac.sarac.cafe.exception;

public class CafeNoiseException {

    public static class CafeNoiseNotRecordedException extends RuntimeException {
        public CafeNoiseNotRecordedException() {
            super("해당 카페는 소음이 측정된 적이 없습니다.");
        }
    }
}
