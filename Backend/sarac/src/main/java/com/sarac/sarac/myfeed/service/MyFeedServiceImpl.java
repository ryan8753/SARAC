package com.sarac.sarac.myfeed.service;

import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.book.repository.BookRepository;
import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.library.repository.LibraryRepository;
import com.sarac.sarac.library.type.LibraryType;
import com.sarac.sarac.myfeed.dto.response.MyFeedLibraryRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfoRes;
import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.entitiy.UserHashtag;
import com.sarac.sarac.user.repository.UserHashtagRepository;
import com.sarac.sarac.user.repository.UserRepository;
import com.sarac.sarac.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("myFeedService")
public class MyFeedServiceImpl implements MyFeedService {

    private static final String ME = "me";
    private static final String OPEN = "open";
    private static final String PRIVATE = "private";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHashtagRepository userHashtagRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JwtUtil jwtUtil;

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

    // 서재 정보 가져오기
    @Override
    public Map<String, List<MyFeedLibraryRes>> getLibraryList(String token, Long userId) {
        Map<String, List<MyFeedLibraryRes>> resultMap = new HashMap<>();
        List<MyFeedLibraryRes> libraryList = new ArrayList<>();

        // userId가 현재 user의 id값과 같으면 (자기 자신의 서재를 보고싶은 경우)
        if( userRepository.findOneByKakaoId((Long)jwtUtil.parseJwtToken(token).get("id")).getId() == userId ) {
            List<Library> userBookList = libraryRepository.findByUserId(userId);
            addBooksToLibraryList(libraryList, userBookList);

            resultMap.put(ME, libraryList);

            return resultMap;
        }

        // (타인의 서재를 보고싶은 경우 - 서재 공개 여부 확인 필요)
        Optional<User> userTmp = userRepository.findById(userId);
        userTmp.ifPresent(user -> {
            if( user.getIsLibraryOpen() ) {
                addBooksToLibraryList(libraryList, libraryRepository.findByUserId(user.getId()));

                resultMap.put(OPEN, libraryList);
            }
            else {
                resultMap.put(PRIVATE, libraryList);
            }
        });

        return null; //공개여부 확인 후 리턴
    }

    public List<String> convertUserHashtagListToTagList(List<UserHashtag> userHashtags) {
        return userHashtags.stream().map(UserHashtag::getContent).collect(Collectors.toList());
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

    public void addBooksToLibraryList(List<MyFeedLibraryRes> libraryList, List<Library> userBookList) {
        for(Library userBook : userBookList) {
            Book book = userBook.getBook();

            libraryList.add(
                    MyFeedLibraryRes.builder()
                        .isbn(book.getIsbn())
                        .bookImgUrl(book.getBookImgUrl())
                        .bookTitle(book.getBookTitle())
                        .author(book.getAuthor())
                        .libraryType(userBook.getLibraryType())
                        .build());
        }
    }
}

