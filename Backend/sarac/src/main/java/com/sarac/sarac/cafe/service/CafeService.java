package com.sarac.sarac.cafe.service;

import com.sarac.sarac.cafe.dto.request.CafeRequest;
import com.sarac.sarac.cafe.dto.response.CafeResponse;
import com.sarac.sarac.cafe.exception.CafeException;

import java.util.List;

public interface CafeService {
    CafeResponse.CafeListInMapArea getCafeListInArea(CafeRequest.CafeListMapArea mapArea);
    CafeResponse.CafeInfo getCafeInfo(long cafeId) throws CafeException.CafeNotFoundException;
}
