package com.sarac.sarac.user.controller;

import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.service.UserService;
import com.sarac.sarac.user.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader Map<String,Object> token) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            userService.logout((String) token.get("authorization"));
            resultMap.put("message", "success");
        }catch(Exception e) {
            e.printStackTrace();
            resultMap.put("message", "fail");
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> myProfile(@RequestHeader Map<String, Object> header) {
        Map<String, Object> resultMap = new HashMap<>();
        Claims claims = jwtUtil.parseJwtToken((String)header.get("authorization"));
        resultMap.put("id", claims.get("id"));
        resultMap.put("authority", claims.get("authority"));
        resultMap.put("nickname", claims.get("nickname"));
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/auth/verify")
    public ResponseEntity<Void> verifyToken(@RequestHeader Map<String, Object> header) {
        String token = (String) header.get("authorization");
//        log.info("토큰 : {}", token);
        return new ResponseEntity<Void>(jwtUtil.validateJwtToken(token));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestHeader Map<String,Object> token) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            userService.deleteUser((String) token.get("authorization"));
            resultMap.put("message", "success");
        }catch(Exception e) {
            e.printStackTrace();
            resultMap.put("message", "fail");
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }



}