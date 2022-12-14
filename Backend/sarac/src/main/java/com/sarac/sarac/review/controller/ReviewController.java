package com.sarac.sarac.review.controller;

import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.payload.request.ReviewCommentRequest;
import com.sarac.sarac.review.payload.response.RandomReviewDTO;
import com.sarac.sarac.review.payload.response.ReviewDTO;
import com.sarac.sarac.review.payload.response.ReviewDetailDTO;
import com.sarac.sarac.review.payload.response.ReviewListDTO;
import com.sarac.sarac.review.payload.request.ReviewRequest;
import com.sarac.sarac.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> registerReview(@RequestPart ReviewRequest review, @RequestPart(value = "files", required = false) List<MultipartFile> files) throws IOException{

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
                Long id = reviewService.registerReview(review);
                reviewService.uploadReviewFile(files,id);
            }else {
                Long id = reviewService.registerReview(review);
            }
            resultMap.put("message", "success");

        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("message", "업로드실패");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);

    }


    @GetMapping("{id}")
    public ResponseEntity<ReviewDTO> showReview( @PathVariable Long id) {
        Map<String, Object> resultMap = new HashMap<>();
        ReviewDTO reviewDTO = null;
        try {
            reviewDTO = reviewService.showReview(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ReviewDTO>(reviewDTO, HttpStatus.OK);
    }

    //책에 대한 리뷰 목록
    @GetMapping("/book/{isbn}")
    public ResponseEntity<List<ReviewListDTO>> showBookReviewList( @PathVariable String isbn) {
        Map<String, Object> resultMap = new HashMap<>();
        List<ReviewListDTO> reviewListDTOS = null;
        try {
            reviewListDTOS = reviewService.showBookReviewList(isbn);
//            resultMap.put("message", "success");
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

    @PutMapping ("/update")
    public ResponseEntity<Map<String, Object>> updateReview(@RequestPart ReviewRequest review, @RequestPart(value = "files", required = false) List<MultipartFile> files,@RequestPart Long id) throws IOException{
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

    // 리뷰 상세보기
    @GetMapping("/detail/{id}")
    public ResponseEntity<ReviewDetailDTO> showDetailReview(@PathVariable Long id, @RequestHeader Map<String, Object> token) {
        ReviewDetailDTO reviewDetailDTO = null;
        try {
            reviewDetailDTO = reviewService.showDetailReview(id, token);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ReviewDetailDTO>(reviewDetailDTO, HttpStatus.OK);
    }

    // 피드모아보기
    // TODO: 2022-09-22 토큰에서 userID를 뽑아오도록 변경 필요
    @GetMapping("/feeds")
    public ResponseEntity<List<RandomReviewDTO>> showRandomFeeds(@RequestHeader Map<String, Object> token) {
        List<RandomReviewDTO> randomReviewDTOList = new ArrayList<>();
        try {
            randomReviewDTOList = reviewService.showRandomFeeds(token);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<RandomReviewDTO>>(randomReviewDTOList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<RandomReviewDTO>>(randomReviewDTOList, HttpStatus.OK);
    }

    // 리뷰 댓글 추가하기
    @PostMapping("/comment")
    public ResponseEntity<Map<String, Object>> registComment(@RequestBody ReviewCommentRequest reviewCommentRequest, @RequestHeader Map<String, Object> token){
        Map<String, Object> resultMap = new HashMap<>();
        Long result = 0L;
        try {
            result = reviewService.registComment(reviewCommentRequest, token);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("message", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(result<=0){
            resultMap.put("message", "등록 실패");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    // 리뷰 댓글 삭제하기
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable Long commentId){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            reviewService.deleteComment(commentId);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("message", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }


    @GetMapping("/like/{reviewId}")
    public ResponseEntity<Map<String, Object>> toggleReviewLike(@PathVariable("reviewId") Long reviewId, @RequestHeader Map<String, Object> token){
        Map<String, Object> resultMap = new HashMap<>();
        try {
            reviewService.toggleReviewLike(token, reviewId);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("message", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

}
