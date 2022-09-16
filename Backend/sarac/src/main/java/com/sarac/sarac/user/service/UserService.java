package com.sarac.sarac.user.service;

import com.sarac.sarac.user.dto.UserDto;
import com.sarac.sarac.user.entitiy.User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService extends OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    User findByKakaoId(Long kakaoId);
    void logout(String token);

    String findByImagePath(Long kakaoId);

    void updateUser(User user, String token);

    UserDto viewUserInfo(String token);


    void deleteUser(String token);
}
