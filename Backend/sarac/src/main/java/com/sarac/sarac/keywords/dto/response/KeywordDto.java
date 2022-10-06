package com.sarac.sarac.keywords.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KeywordDto {
    private String text;
    private int value;
}
