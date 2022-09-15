package com.sarac.sarac.user.service;

import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.repository.UserRepository;
import com.sarac.sarac.user.util.JwtUtil;
import com.sarac.sarac.user.util.KakaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    KakaoUtil kakaoUtil;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public User findByKakaoId(Long kakaoId) {
        return userRepository.findOneByKakaoId(kakaoId);
    }

    @Override
    public void logout(String token) {
        User user = userRepository.findOneByKakaoId((Long)jwtUtil.parseJwtToken(token).get("id"));
        kakaoUtil.kakaoLogout((Long)jwtUtil.parseJwtToken(token).get("id"));
    }

    @Override
    public String findByImagePath(Long kakaoId) {
        return userRepository.findOneByKakaoId(kakaoId).getImagePath();
    }

    @Override
    public void deleteUser(String token) {
        kakaoUtil.unlink((Long)jwtUtil.parseJwtToken(token).get("id"));
        userRepository.deleteUserByKakaoId((Long)jwtUtil.parseJwtToken(token).get("id"));

    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("USER")), attributes, "id");
    }
}
