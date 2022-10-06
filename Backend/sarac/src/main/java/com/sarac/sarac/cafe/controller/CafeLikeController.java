package com.sarac.sarac.cafe.controller;

import com.sarac.sarac.cafe.dto.request.CafeLikeRequest;
import com.sarac.sarac.cafe.dto.response.CafeLikeResponse;
import com.sarac.sarac.cafe.entity.Cafe;
import com.sarac.sarac.cafe.exception.CafeException;
import com.sarac.sarac.cafe.exception.CafeLikeException;
import com.sarac.sarac.cafe.service.CafeLikeService;
import com.sarac.sarac.cafe.service.CafeService;
import com.sarac.sarac.user.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cafeLike")
public class CafeLikeController {

    @Autowired
    private CafeLikeService cafeLikeService;

    @PutMapping("/evalCafe")
    public ResponseEntity<String> setGoodBadInfo(@RequestBody CafeLikeRequest.CafeLikeInfo cafeLikeInfo) {
        try {
            cafeLikeService.setGoodBadInfo(cafeLikeInfo);
        } catch(CafeException.CafeNotFoundException | UserException.UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @DeleteMapping("/evalCafe")
    public ResponseEntity<String> removeGoodBadInfo(@RequestBody CafeLikeRequest.CafeLikeInfo cafeLikeInfo) {
        try {
            cafeLikeService.removeGoodBadInfo(cafeLikeInfo);
        } catch(CafeException.CafeNotFoundException | UserException.UserNotFoundException | CafeLikeException.NoSuchCafeLikeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/cafeLikeInfo")
    public ResponseEntity<?> getGoodBadInfo(@RequestBody CafeLikeRequest.CafeLikeStatus cafeLikeStatus) {
        Boolean likesCafe = null;
        try {
            likesCafe = cafeLikeService.getGoodBadInfo(cafeLikeStatus);
        } catch(CafeException.CafeNotFoundException | UserException.UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(likesCafe, HttpStatus.OK);
    }

    @GetMapping("/cafeGoodBadCounts/{cafeId}")
    public ResponseEntity<?> getGoodBadCounts(@PathVariable long cafeId) {
        CafeLikeResponse.GoodBadCounts goodBadCounts = null;
        try {
            goodBadCounts = cafeLikeService.getGoodBadCounts(cafeId);
        } catch(CafeException.CafeNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CafeLikeResponse.GoodBadCounts>(goodBadCounts, HttpStatus.OK);
    }
}
