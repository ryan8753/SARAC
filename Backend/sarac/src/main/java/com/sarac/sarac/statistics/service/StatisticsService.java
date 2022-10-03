package com.sarac.sarac.statistics.service;


import com.sarac.sarac.review.payload.response.ReviewHashtagDTO;
import com.sarac.sarac.statistics.payload.response.StatisticsDTO;

import java.util.List;

public interface StatisticsService {

    StatisticsDTO getStatistics(Long userId);

    List<ReviewHashtagDTO> getHashtags(Long userId);



}
