package com.sarac.sarac.myfeed.controller;

import com.sarac.sarac.myfeed.dto.response.MyFeedLibraryRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfoRes;
import com.sarac.sarac.myfeed.service.MyFeedService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/myfeed")
@Log4j2
public class MyFeedController {

    @Autowired
    private MyFeedService myFeedService;

    // 사용자 상세정보
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
    
    @GetMapping("/library")
    public  ResponseEntity<List<MyFeedLibraryRes>> getBookList(Long userId) {
        List<MyFeedLibraryRes> bookList = new ArrayList<>(); //고치기
        
        return ResponseEntity.status(200).body(bookList);
    }
}
