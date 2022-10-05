package com.sarac.sarac.statistics.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StatisticsDTO {

    private int totalPrice;

    private int totalPage;

    private Double totalScore;

    private Integer totalNoise;

    private List<TopGenreDTO> topGenre;

}
