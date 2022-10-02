package com.sarac.sarac.statistics.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatisticsDTO {

    private int totalPrice;

    private Double totalScore;

    private Integer totalNoise;

}