package com.sarac.sarac.myfeed.service;

import com.sarac.sarac.myfeed.dto.response.MyFeedUserRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserResInfoRes;

import java.util.List;

public interface MyFeedService {
    MyFeedUserResInfoRes getInfoByUserInfo(Long userId);

    List<MyFeedUserRes> getUserList(String keyWord);
}
