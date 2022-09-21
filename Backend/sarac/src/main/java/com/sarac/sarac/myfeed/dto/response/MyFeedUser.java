package com.sarac.sarac.myfeed.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MyFeedUser {

    private Long userId;
    private String imagePath;
    private String nickname;
    private List<String> userHashtag;

}
