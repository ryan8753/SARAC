package com.sarac.sarac.statistics.service;


import com.sarac.sarac.statistics.payload.response.StatisticsDTO;

public interface StatisticsService {

    StatisticsDTO getStatistics(Long userId);



}
