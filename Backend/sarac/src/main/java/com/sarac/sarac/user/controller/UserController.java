package com.sarac.sarac.user.controller;

import com.sarac.sarac.user.dto.UserDto;
import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.service.UserService;
import com.sarac.sarac.user.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
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
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> myProfile(@RequestHeader Map<String, Object> token) {

        Map<String, Object> resultMap = new HashMap<>();
        UserDto user =  userService.viewUserInfo((String) token.get("authorization"));
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestHeader Map<String,Object> token) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            userService.deleteUser((String) token.get("authorization"));
            resultMap.put("message", "success");
        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity updateUser(@RequestBody User user, @RequestHeader Map<String,Object> token) {
        HashMap<String, Object> resultMap = new HashMap<>();
        try {
            userService.updateUser(user, (String) token.get("authorization"));
            resultMap.put("message", "success");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(resultMap, HttpStatus.OK);
    }

}
