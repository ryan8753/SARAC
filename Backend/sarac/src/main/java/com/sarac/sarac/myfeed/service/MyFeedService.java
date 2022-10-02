package com.sarac.sarac.myfeed.service;

import com.sarac.sarac.myfeed.dto.response.MyFeedLibraryRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedReviewListRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfoRes;
import com.sarac.sarac.user.entitiy.User;

import java.util.List;
import java.util.Map;

public interface MyFeedService {
    MyFeedUserInfoRes getInfoByUserInfo(Long userId);

    List<MyFeedUserRes> getUserList(String keyWord);

    Map<String, List<MyFeedLibraryRes>> getLibraryList(String token, Long userId);

    Map<String, List<MyFeedReviewListRes>> getReviewList(String token, Long userId);
}
