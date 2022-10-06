package com.sarac.sarac.keywords.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class KeywordListDto {
    private List<KeywordDto> keywords;
}
