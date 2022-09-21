package com.sarac.sarac.myfeed.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyFeedUserInfo extends MyFeedUser{

    private Long statusDone;
    private Long statusIng;
    private Long statusTodo;

    @Builder
    public MyFeedUserInfo(Long userId, String imagePath, String nickname, List<String> userHashtag, Long statusDone, Long statusIng, Long statusTodo) {
        super(userId, imagePath, nickname, userHashtag);
        this.statusDone = statusDone;
        this.statusIng = statusIng;
        this.statusTodo = statusTodo;
    }
}
