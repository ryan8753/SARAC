package com.sarac.sarac.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long userId;

    private Long kakaoId;

    private String nickname;

    private String imagePath;

    private boolean isLabraryOpen;

    private boolean isReviewOpen;

}
