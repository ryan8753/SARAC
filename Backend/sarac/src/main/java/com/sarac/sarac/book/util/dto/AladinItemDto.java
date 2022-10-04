package com.sarac.sarac.book.util.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class AladinItemDto {
    private String description;
    private String isbn13;
    private int priceStandard;
    private String categoryName;
    private String publisher;
    private String cover;

    private AladinBookInfoDto bookinfo;
}
