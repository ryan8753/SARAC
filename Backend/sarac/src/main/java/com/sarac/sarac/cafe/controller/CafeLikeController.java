package com.sarac.sarac.cafe.controller;

import com.sarac.sarac.cafe.dto.request.CafeLikeRequest;
import com.sarac.sarac.cafe.exception.CafeException;
import com.sarac.sarac.cafe.exception.CafeLikeException;
import com.sarac.sarac.cafe.service.CafeLikeService;
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
    public ResponseEntity<String> setGoodOrBadInfo(@RequestBody CafeLikeRequest.CafeLikeInfo cafeLikeInfo) {
        try {
            cafeLikeService.setGoodOrBadInfo(cafeLikeInfo);
        } catch(CafeException.CafeNotFoundException | UserException.UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @DeleteMapping("/evalCafe")
    public ResponseEntity<String> removeGoodOrBadInfo(@RequestBody CafeLikeRequest.CafeLikeInfo cafeLikeInfo) {
        try {
            cafeLikeService.removeGoodOrBadInfo(cafeLikeInfo);
        } catch(CafeException.CafeNotFoundException | UserException.UserNotFoundException | CafeLikeException.NoSuchCafeLikeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/cafeLikeInfo")
    public ResponseEntity<?> getGoodOrBadInfo(@RequestBody CafeLikeRequest.CafeLikeStatus cafeLikeStatus) {
        Boolean likesCafe = null;
        try {
            likesCafe = cafeLikeService.getGoodOrBadInfo(cafeLikeStatus);
        } catch(CafeException.CafeNotFoundException | UserException.UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(likesCafe, HttpStatus.OK);
    }
}
