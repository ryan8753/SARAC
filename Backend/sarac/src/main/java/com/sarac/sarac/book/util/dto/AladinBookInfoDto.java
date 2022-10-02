package com.sarac.sarac.book.util.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class AladinBookInfoDto {
    private int itemPage;
    private List<AladinAuthorDto> authors;
}
