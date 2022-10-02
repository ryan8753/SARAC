package com.sarac.sarac.myfeed.controller;

import com.sarac.sarac.myfeed.dto.response.MyFeedLibraryRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedReviewListRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfoRes;
import com.sarac.sarac.myfeed.service.MyFeedService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/myfeed")
@Log4j2
public class MyFeedController {

    @Autowired
    private MyFeedService myFeedService;

    // 유저 상세정보
    @GetMapping("/userinfo")
    public ResponseEntity<MyFeedUserInfoRes> getUserInfo(Long userId) {
        MyFeedUserInfoRes userInfo = myFeedService.getInfoByUserInfo(userId);

        return ResponseEntity.status(200).body(userInfo);
    }

    // 유저 검색
    @GetMapping("/userlist")
    public ResponseEntity<List<MyFeedUserRes>> getUserList(String keyWord) {
        List<MyFeedUserRes> userList = myFeedService.getUserList(keyWord);

        return ResponseEntity.status(200).body(userList);
    }

    // 서재
    @GetMapping("/library")
    public ResponseEntity<Map<String, List<MyFeedLibraryRes>>> getLibraryList(@RequestHeader Map<String,Object> token, Long userId) {
        Map<String, List<MyFeedLibraryRes>> libraryList = myFeedService.getLibraryList((String) token.get("authorization"), userId);
        
        return ResponseEntity.status(200).body(libraryList);
    }

    // 리뷰
    @GetMapping("/reviewlist")
    public ResponseEntity<Map<String, List<MyFeedReviewListRes>>> getReviewList(@RequestHeader Map<String,Object> token, Long userId) {
        Map<String, List<MyFeedReviewListRes>> reviewList = myFeedService.getReviewList((String) token.get("authorization"), userId);

        return ResponseEntity.status(200).body(reviewList);
    }

}
