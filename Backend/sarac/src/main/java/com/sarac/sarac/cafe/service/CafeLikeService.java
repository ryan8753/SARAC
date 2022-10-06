package com.sarac.sarac.cafe.service;

import com.sarac.sarac.cafe.dto.request.CafeLikeRequest;

import java.util.Optional;

public interface CafeLikeService {
    void setGoodOrBadInfo(CafeLikeRequest.CafeLikeInfo cafeLikeInfo);
    void removeGoodOrBadInfo(CafeLikeRequest.CafeLikeInfo cafeLikeInfo);
    Boolean getGoodOrBadInfo(CafeLikeRequest.CafeLikeStatus cafeLikeStatus);
}
