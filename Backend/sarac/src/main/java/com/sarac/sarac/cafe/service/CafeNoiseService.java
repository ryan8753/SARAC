package com.sarac.sarac.cafe.service;

import com.sarac.sarac.cafe.dto.request.CafeNoiseRequest;
import com.sarac.sarac.cafe.dto.response.CafeNoiseResponse;

public interface CafeNoiseService {
    void recordCafeNoise(CafeNoiseRequest.CafeNoiseInfo cafeNoiseInfo);
    CafeNoiseResponse.NoiseInfo getLastNoiseRecord(long cafeId);
    double averageCafeNoise(long cafeId);
}
