package com.sarac.sarac.user.exception;

public class UserException {

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException() {
            super("해당하는 유저가 존재하지 않습니다.");
        }
    }
}
