package com.sarac.sarac.myfeed.controller;

import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/myfeed")
@AllArgsConstructor
@CrossOrigin("*")
@Log4j2
public class MyFeedController {

    @Autowired
    private MyFeedUserInfo myFeedUserInfo;

//    public ResponseEntity<Map<String, Object>> myFeedUserInfo(Long userId) {
//        MyFeedUserInfo userInfo = MyFeedUserInfo.builder()
//                .userId()
//        return new ResponseEntity<Map<String,Object>>("result", status);
//    }
}
