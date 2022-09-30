package com.sarac.sarac.user.controller;

import com.sarac.sarac.user.dto.UserDto;
import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.service.UserService;
import com.sarac.sarac.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
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

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> fileUpload(@RequestHeader Map<String,Object> token, @RequestParam("file") MultipartFile file) throws IOException {

        Map<String, String> resultMap = new HashMap<>();
        HttpStatus status = null;
        // 이미지 파일만 업로드 가능
        try {
            if (file.getContentType().startsWith("image")) {
                String image = userService.uploadFile(file, (String) token.get("authorization"));
                resultMap.put("imagePath", image);
            } else {
                resultMap.put("message", "이미지 파일만 업로드 가능합니다.");
            }
            return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("message", "파일 이미지 용량 초과");
            return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.BAD_REQUEST);
        }



    }

}
