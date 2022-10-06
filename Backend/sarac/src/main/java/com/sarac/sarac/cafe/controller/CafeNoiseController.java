package com.sarac.sarac.cafe.controller;

import com.sarac.sarac.cafe.dto.request.CafeNoiseRequest;
import com.sarac.sarac.cafe.dto.response.CafeNoiseResponse;
import com.sarac.sarac.cafe.exception.CafeException;
import com.sarac.sarac.cafe.exception.CafeNoiseException;
import com.sarac.sarac.cafe.service.CafeNoiseService;
import com.sarac.sarac.user.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cafeNoise")
public class CafeNoiseController {

    @Autowired
    private CafeNoiseService cafeNoiseService;

    @PutMapping("/record")
    public ResponseEntity<String> recordCafeNoise(@RequestBody CafeNoiseRequest.CafeNoiseInfo cafeNoiseInfo) {
        try {
            cafeNoiseService.recordCafeNoise(cafeNoiseInfo);
        } catch(CafeException.CafeNotFoundException | UserException.UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/lastRecord/{cafeId}")
    public ResponseEntity<?> getLastNoiseRecord(@PathVariable long cafeId) {
        try {
            CafeNoiseResponse.NoiseInfo noiseInfo = cafeNoiseService.getLastNoiseRecord(cafeId);
            return new ResponseEntity<CafeNoiseResponse.NoiseInfo>(noiseInfo, HttpStatus.OK);
        } catch(CafeException.CafeNotFoundException | CafeNoiseException.CafeNoiseNotRecordedException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
