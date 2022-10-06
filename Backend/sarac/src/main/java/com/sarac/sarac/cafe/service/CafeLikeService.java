package com.sarac.sarac.cafe.service;

import com.sarac.sarac.cafe.dto.request.CafeLikeRequest;
import com.sarac.sarac.cafe.dto.response.CafeLikeResponse;

import java.util.Optional;

public interface CafeLikeService {
    void setGoodBadInfo(CafeLikeRequest.CafeLikeInfo cafeLikeInfo);
    void removeGoodBadInfo(CafeLikeRequest.CafeLikeInfo cafeLikeInfo);
    Boolean getGoodBadInfo(CafeLikeRequest.CafeLikeStatus cafeLikeStatus);
    CafeLikeResponse.GoodBadCounts getGoodBadCounts(long cafeId);
}
