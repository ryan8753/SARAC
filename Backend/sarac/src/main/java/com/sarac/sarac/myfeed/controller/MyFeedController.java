package com.sarac.sarac.myfeed.controller;

import com.sarac.sarac.myfeed.dto.response.MyFeedUser;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfo;
import com.sarac.sarac.myfeed.service.MyFeedService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/my-feed")
@Log4j2
public class MyFeedController {

    @Autowired
    private MyFeedService myFeedService;

    // 사용자 상세정보
    @GetMapping("/user-info")
    public ResponseEntity<MyFeedUserInfo> getUserInfo(Long userId) {
        MyFeedUserInfo userInfo = myFeedService.getInfoByUserInfo(userId);

        return ResponseEntity.status(200).body(userInfo);
    }

    // 유저 검색
    @GetMapping("/user-list")
    public ResponseEntity<List<MyFeedUser>> getUserList(String keyWord) {
        List<MyFeedUser> userList = myFeedService.getUserList(keyWord);

        return ResponseEntity.status(200).body(userList);
    }
}
