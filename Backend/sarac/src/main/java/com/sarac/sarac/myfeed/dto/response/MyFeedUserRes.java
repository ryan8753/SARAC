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
public class MyFeedUserRes {

    private Long userId;
    private String imagePath;
    private String nickname;
    private List<String> userHashtag;

}
