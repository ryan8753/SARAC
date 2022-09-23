package com.sarac.sarac.myfeed.service;

import com.sarac.sarac.myfeed.dto.response.MyFeedLibraryRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfoRes;
import com.sarac.sarac.user.entitiy.User;

import java.util.List;

public interface MyFeedService {
    MyFeedUserInfoRes getInfoByUserInfo(Long userId);

    List<MyFeedUserRes> getUserList(String keyWord);

    List<MyFeedLibraryRes> getBookList(Long userId);

}
