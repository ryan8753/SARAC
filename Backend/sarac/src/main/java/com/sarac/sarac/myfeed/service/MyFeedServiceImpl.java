package com.sarac.sarac.myfeed.service;

import com.sarac.sarac.library.repository.LibraryRepository;
import com.sarac.sarac.library.type.LibraryType;
import com.sarac.sarac.myfeed.dto.response.MyFeedUser;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfo;
import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.entitiy.UserHashtag;
import com.sarac.sarac.user.repository.UserHashtagRepository;
import com.sarac.sarac.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("myFeedService")
public class MyFeedServiceImpl implements MyFeedService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHashtagRepository userHashtagRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    // 유저 상세 정보
    @Override
    public MyFeedUserInfo getInfoByUserInfo(Long userId) {
        MyFeedUserInfo userInfo = new MyFeedUserInfo();

        Optional<User> userTmp = userRepository.findById(userId);
        userTmp.ifPresent(user -> {
            userInfo.setUserId(user.getId());
            userInfo.setImagePath(user.getImagePath());
            userInfo.setNickname(user.getNickname());

            List<UserHashtag> userHashtags = userHashtagRepository.findByUserId(userId);
            List<String> hashtagList = new ArrayList<>();
            for(UserHashtag tag : userHashtags) {
                hashtagList.add(tag.getContent());
            }
            userInfo.setUserHashtag(hashtagList);

            Long wish = libraryRepository.countByUserIdAndLibraryType(userId, LibraryType.WISH);
            Long reading = libraryRepository.countByUserIdAndLibraryType(userId, LibraryType.READING);
            Long read = libraryRepository.countByUserIdAndLibraryType(userId, LibraryType.READ);

            userInfo.setWish(wish);
            userInfo.setReading(reading);
            userInfo.setRead(read);
        });

        return userInfo;
    }

    // 유저 검색
    @Override
    public List<MyFeedUser> getUserList(String keyWord) {
        // 검색된 유저 정보 리스트 넣을 배열
        List<MyFeedUser> userList = new ArrayList<>();

        // 닉네임 기반 검색 결과
        List<User> searchNickname = userRepository.findByNicknameContaining(keyWord);
        for(User user : searchNickname) {
            List<String> hashtagList = new ArrayList<>();
            List<UserHashtag> userHashtags = userHashtagRepository.findByUserId(user.getId());

            for(UserHashtag tags : userHashtags) {
                hashtagList.add(tags.getContent());
            }

            userList.add(
                    MyFeedUser.builder()
                            .userId(user.getId())
                            .imagePath(user.getImagePath())
                            .nickname(user.getNickname())
                            .userHashtag(hashtagList)
                            .build());
        }

        // 회원 태그(코드)기반 검색 결과 (keyword가 숫자면 수행)
        if(keyWord != null && keyWord.matches("[0-9]+")) {
            List<User> searchUserCode = userRepository.findByIdContaining(Long.parseLong(keyWord));
            for(User user : searchUserCode) {
                List<String> hashtagList = new ArrayList<>();
                List<UserHashtag> userHashtags = userHashtagRepository.findByUserId(user.getId());

                for(UserHashtag tags : userHashtags) {
                    hashtagList.add(tags.getContent());
                }

                userList.add(
                        MyFeedUser.builder()
                                .userId(user.getId())
                                .imagePath(user.getImagePath())
                                .nickname(user.getNickname())
                                .userHashtag(hashtagList)
                                .build());
            }
        }

        return userList;
    }
}
