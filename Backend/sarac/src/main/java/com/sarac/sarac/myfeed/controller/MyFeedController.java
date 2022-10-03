package com.sarac.sarac.myfeed.controller;

import com.sarac.sarac.myfeed.dto.response.MyFeedLibraryRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedReviewListRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfoRes;
import com.sarac.sarac.myfeed.service.MyFeedService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "개인피드 유저 상세정보", notes = "회원 Id를 확인하여 상세정보를 전송합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<MyFeedUserInfoRes> getUserInfo(Long userId) {
        MyFeedUserInfoRes userInfo = myFeedService.getInfoByUserInfo(userId);

        return ResponseEntity.status(200).body(userInfo);
    }

    // 유저 검색
    @GetMapping("/userlist")
    @ApiOperation(value = "유저 검색", notes = "keyword를 확인하여, 해당 키워드를 포함하거나 회원코드가 일치하는 유저들의 목록을 전송합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<MyFeedUserRes>> getUserList(String keyWord) {
        List<MyFeedUserRes> userList = myFeedService.getUserList(keyWord);

        return ResponseEntity.status(200).body(userList);
    }

    // 서재
    @GetMapping("/library")
    @ApiOperation(value = "서재 정보", notes = "회원 Id를 확인하여 서재 정보를 '내서재', '공개 서재', '비공개 서재'로 확인하여 전송합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String, List<MyFeedLibraryRes>>> getLibraryList(@RequestHeader Map<String,Object> token, Long userId) {
        Map<String, List<MyFeedLibraryRes>> libraryList = myFeedService.getLibraryList((String) token.get("authorization"), userId);
        
        return ResponseEntity.status(200).body(libraryList);
    }

    // 리뷰
    @GetMapping("/reviewlist")
    @ApiOperation(value = "개인 리뷰 목록", notes = "회원 Id를 확인하여 작성항 리뷰 목록을 '내 리뷰', '공개 리뷰', '비공개 리뷰'로 확인하여 전송합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String, List<MyFeedReviewListRes>>> getReviewList(@RequestHeader Map<String,Object> token, Long userId) {
        Map<String, List<MyFeedReviewListRes>> reviewList = myFeedService.getReviewList((String) token.get("authorization"), userId);

        return ResponseEntity.status(200).body(reviewList);
    }

}
