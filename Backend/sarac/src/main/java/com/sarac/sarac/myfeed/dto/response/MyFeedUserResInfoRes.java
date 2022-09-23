package com.sarac.sarac.myfeed.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class MyFeedUserResInfoRes extends MyFeedUserRes {

    private Long wish;
    private Long reading;
    private Long read;

    public MyFeedUserResInfoRes(Long userId, String imagePath, String nickname, List<String> userHashtag, Long wish, Long reading, Long read) {
        super(userId, imagePath, nickname, userHashtag);
        this.wish = wish;
        this.reading = reading;
        this.read = read;
    }
}
