package com.sarac.sarac.myfeed.service;

import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfo;
import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("myFeedService")
public class MyFeedServiceImpl implements MyFeedService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public MyFeedUserInfo getInfoByUserInfo(Long userId) {
        MyFeedUserInfo userInfo = new MyFeedUserInfo();

        Optional<User> userTmp = userRepository.findById(userId);
        userTmp.ifPresent(user -> {
            userInfo.setUserId(user.getId());
            userInfo.setImagePath(user.getImagePath());
            userInfo.setNickname(user.getNickname());
        });



        return null;
    }
}
