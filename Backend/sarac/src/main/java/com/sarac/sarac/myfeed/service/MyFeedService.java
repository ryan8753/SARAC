package com.sarac.sarac.myfeed.service;

import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfo;

public interface MyFeedService {
    MyFeedUserInfo getInfoByUserInfo(Long userId);
}
