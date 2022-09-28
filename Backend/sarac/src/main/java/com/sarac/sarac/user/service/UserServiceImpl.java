package com.sarac.sarac.user.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sarac.sarac.global.util.FileUpload;
import com.sarac.sarac.user.dto.UserDto;
import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.repository.UserRepository;
import com.sarac.sarac.user.util.JwtUtil;
import com.sarac.sarac.user.util.KakaoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements  UserService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final FileUpload fileUpload;

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
    public String uploadFile(MultipartFile file, String token) throws IOException {
        Long kakaoId = ((Long) jwtUtil.parseJwtToken(token).get("id"));

        String newImagePath = fileUpload.fileUpload(file,kakaoId,"profile");
        User user = userRepository.findOneByKakaoId(kakaoId);
        user.setImagePath(newImagePath);
        userRepository.save(user);

        return newImagePath;

    }




    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("USER")), attributes, "id");
    }

    @Transactional
    @Override
    public void updateUser(User user, String token) {
        User originUser = userRepository.findOneByKakaoId((Long)jwtUtil.parseJwtToken(token).get("id"));

        if (user.getNickname() != null) {
            originUser.setNickname(user.getNickname());
        }
        if (user.getIsLibraryOpen() != null) {
            originUser.setIsLibraryOpen(user.getIsLibraryOpen());
        }
        if (user.getIsReviewOpen() != null) {
            originUser.setIsReviewOpen(user.getIsReviewOpen());
        }
        userRepository.save(originUser);

    }

    @Override
    public UserDto viewUserInfo(String token) {
        User originUser = userRepository.findOneByKakaoId((Long)jwtUtil.parseJwtToken(token).get("id"));

        UserDto userDto = new UserDto();
        userDto.setUserId(originUser.getId());
        userDto.setKakaoId(originUser.getKakaoId());
        userDto.setNickname(originUser.getNickname());
        userDto.setImagePath(originUser.getImagePath());
        userDto.setReviewOpen(originUser.getIsReviewOpen());
        userDto.setLabraryOpen(originUser.getIsLibraryOpen());

        return userDto;
    }
}
