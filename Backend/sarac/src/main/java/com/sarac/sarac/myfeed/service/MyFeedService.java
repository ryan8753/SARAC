package com.sarac.sarac.myfeed.service;

import com.sarac.sarac.myfeed.dto.response.MyFeedUser;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfo;

import java.util.List;

public interface MyFeedService {
    MyFeedUserInfo getInfoByUserInfo(Long userId);

    List<MyFeedUser> getUserList(String keyWord);
}
