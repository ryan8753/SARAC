package com.sarac.sarac.myfeed.service;

import com.sarac.sarac.library.repository.LibraryRepository;
import com.sarac.sarac.library.type.LibraryType;
import com.sarac.sarac.myfeed.dto.response.MyFeedLibraryRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfoRes;
import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.entitiy.UserHashtag;
import com.sarac.sarac.user.repository.UserHashtagRepository;
import com.sarac.sarac.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public MyFeedUserInfoRes getInfoByUserInfo(Long userId) {
        MyFeedUserInfoRes userInfo = new MyFeedUserInfoRes();

        Optional<User> userTmp = userRepository.findById(userId);
        userTmp.ifPresent(user -> {
            userInfo.setUserId(user.getId());
            userInfo.setImagePath(user.getImagePath());
            userInfo.setNickname(user.getNickname());

            List<String> hashtagList = convertUserHashtagListToTagList(userHashtagRepository.findByUserId(user.getId()));
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
    public List<MyFeedUserRes> getUserList(String keyWord) {
        // 검색된 유저 정보 리스트 넣을 배열
        List<MyFeedUserRes> userList = new ArrayList<>();

        // 닉네임 기반 검색 결과
        List<User> searchNickname = userRepository.findByNicknameContaining(keyWord);
        addUsersToUserList(userList, searchNickname);

        // 회원 태그(코드)기반 검색 결과 (keyword가 숫자면 수행)
        if(keyWord != null && keyWord.matches("[0-9]+")) {
            List<User> searchUserCode = userRepository.findByIdContaining(Long.parseLong(keyWord));
            addUsersToUserList(userList, searchUserCode);
        }

        return userList;
    }

    @Override
    public List<MyFeedLibraryRes> getBookList(Long userId) {
        return null; //공개여부 확인 후 리턴
    }

    public void addUsersToUserList(List<MyFeedUserRes> userListArr, List<User> searchedUsers) {
        for(User user : searchedUsers) {
            List<String> hashtagList = convertUserHashtagListToTagList(userHashtagRepository.findByUserId(user.getId()));

            userListArr.add(
                    MyFeedUserRes.builder()
                            .userId(user.getId())
                            .imagePath(user.getImagePath())
                            .nickname(user.getNickname())
                            .userHashtag(hashtagList)
                            .build());
        }
    }

    public List<String> convertUserHashtagListToTagList(List<UserHashtag> userHashtags) {
        return userHashtags.stream().map(UserHashtag::getContent).collect(Collectors.toList());
    }
}

