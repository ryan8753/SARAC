package com.sarac.sarac.book.util.dto;

import lombok.Getter;

@Getter
public class AladinErrorDto implements AladinResponse{
    private int errorCode;
    private String errorMessage;
}
