package com.sarac.sarac.cafe.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sarac.sarac.cafe.dto.request.CafeRequest;
import com.sarac.sarac.cafe.dto.response.CafeResponse;
import com.sarac.sarac.cafe.exception.CafeException;
import com.sarac.sarac.cafe.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cafe")
public class CafeController {

    @Autowired
    private CafeService cafeService;

    @PostMapping("/cafeList")
    public ResponseEntity<CafeResponse.CafeListInMapArea> getCafeListInArea(@RequestBody CafeRequest.CafeListMapArea cafeListMapArea) {
        return new ResponseEntity<>(cafeService.getCafeListInArea(cafeListMapArea), HttpStatus.OK);
    }

    @GetMapping("/cafeInfo/{cafeId}")
    public ResponseEntity<?> getCafeInfo(@PathVariable long cafeId) {
        try {
            CafeResponse.CafeInfo cafeInfo = cafeService.getCafeInfo(cafeId);
            return new ResponseEntity<CafeResponse.CafeInfo>(cafeInfo, HttpStatus.OK);
        } catch(CafeException.CafeNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
