package com.sarac.sarac.review.controller;

import com.sarac.sarac.review.payload.response.ReviewListDTO;
import com.sarac.sarac.review.payload.request.ReviewRequest;
import com.sarac.sarac.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registerReview(@RequestPart ReviewRequest review, @RequestHeader Map<String,Object> token, @RequestPart(value = "files", required = false) List<MultipartFile> files) throws IOException{

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
//         이미지 파일만 업로드 가능
        try {
            if(files!=null) {
                for (MultipartFile file : files) {
                    if (!file.getContentType().startsWith("image")) {
                        resultMap.put("message", "이미지 파일만 업로드 가능합니다.");
                        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
                    }
                }
                Long id = reviewService.registerReview(review,(String) token.get("authorization"));
                reviewService.uploadReviewFile(files,id);
            }else {
                Long id = reviewService.registerReview(review, (String) token.get("authorization"));
            }
            resultMap.put("message", review);

        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("message", "업로드실패");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);

    }

    //개인의 리뷰
    @GetMapping
    public ResponseEntity<List<ReviewListDTO>> showUserReviewList( @RequestHeader Map<String,Object> token) {
        Map<String, Object> resultMap = new HashMap<>();
        List<ReviewListDTO> reviewListDTOS = null;
        try {
            reviewListDTOS = reviewService.showUserReviewList((String) token.get("authorization"));
            resultMap.put("message", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<ReviewListDTO>>(reviewListDTOS, HttpStatus.OK);
    }

    //책에대한 리뷰 목록 표시
    @GetMapping("/book/{isbn}")
    public ResponseEntity<List<ReviewListDTO>> showBookReviewList( @PathVariable String isbn) {
        Map<String, Object> resultMap = new HashMap<>();
        List<ReviewListDTO> reviewListDTOS = null;
        try {
            reviewListDTOS = reviewService.showBookReviewList(isbn);
            resultMap.put("message", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<ReviewListDTO>>(reviewListDTOS, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteReview(@PathVariable Long id){
        Map<String, Object> resultMap = new HashMap<>();
        try{
            reviewService.deleteReview(id);
            resultMap.put("message", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateReview(@RequestPart ReviewRequest review, @RequestPart(value = "files", required = false) List<MultipartFile> files, @PathVariable Long id){
        Map<String, Object> resultMap = new HashMap<>();
        //         이미지 파일만 업로드 가능
        try {
            if(files!=null) {
                for (MultipartFile file : files) {
                    if (!file.getContentType().startsWith("image")) {
                        resultMap.put("message", "이미지 파일만 업로드 가능합니다.");
                        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
                    }
                }
                reviewService.updateReview(review,id,files);

            }else {
                reviewService.updateReview(review,id,null);
            }
            resultMap.put("message", review);

        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("message", "업데이트실패");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }


}
