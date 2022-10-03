package com.sarac.sarac.myfeed.service;

import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.book.repository.BookRepository;
import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.library.repository.LibraryRepository;
import com.sarac.sarac.library.type.LibraryType;
import com.sarac.sarac.myfeed.dto.response.MyFeedLibraryRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedReviewListRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserRes;
import com.sarac.sarac.myfeed.dto.response.MyFeedUserInfoRes;
import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.entity.ReviewPhoto;
import com.sarac.sarac.review.repository.ReviewPhotoRepository;
import com.sarac.sarac.review.repository.ReviewRepository;
import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.entitiy.UserHashtag;
import com.sarac.sarac.user.repository.UserHashtagRepository;
import com.sarac.sarac.user.repository.UserRepository;
import com.sarac.sarac.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("myFeedService")
@RequiredArgsConstructor
public class MyFeedServiceImpl implements MyFeedService {

    private static final String ME = "me";
    private static final String OPEN = "open";
    private static final String PRIVATE = "private";

    private final UserRepository userRepository;

    private final UserHashtagRepository userHashtagRepository;

    private final LibraryRepository libraryRepository;

    private final ReviewRepository reviewRepository;

    private final ReviewPhotoRepository reviewPhotoRepository;

    private final JwtUtil jwtUtil;

    // 유저 상세 정보
    @Override
    public MyFeedUserInfoRes getInfoByUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        return MyFeedUserInfoRes.builder()
                .userId(user.getId())
                .imagePath(user.getImagePath())
                .nickname(user.getNickname())
                .userHashtag(convertUserHashtagListToTagList(userHashtagRepository.findByUserId(user.getId())))
                .wish(libraryRepository.countByUserIdAndLibraryType(user.getId(), LibraryType.WISH))
                .reading(libraryRepository.countByUserIdAndLibraryType(user.getId(), LibraryType.READING))
                .read(libraryRepository.countByUserIdAndLibraryType(user.getId(), LibraryType.READ))
                .build();
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
        if(keyWord != null && keyWord.matches("[0-9]+") && keyWord.length() == 4) {
            int startIdx = -1;
            for(int i = 0; i < keyWord.length(); i++) {
                if(keyWord.charAt(i) != '0') {
                    startIdx = i;
                    break;
                }
            }

            Optional<User> searchUserCode = userRepository.findById(Long.parseLong(keyWord.substring(startIdx)));
            searchUserCode.ifPresent(user -> {
                List<User> searchCode = new ArrayList<>();
                searchCode.add(user);
                addUsersToUserList(userList, searchCode);
            });
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

        return resultMap;
    }

    // 리뷰목록 가져오기
    @Override
    public Map<String, List<MyFeedReviewListRes>> getReviewList(String token, Long userId) {
        Map<String, List<MyFeedReviewListRes>> resultMap = new HashMap<>();

        // userId가 현재 user의 id값과 같으면 (자기 자신의 리뷰를 보고싶은 경우)
        if( userRepository.findOneByKakaoId((Long)jwtUtil.parseJwtToken(token).get("id")).getId() == userId ) {
            List<Review> userReviewList = reviewRepository.findAllByUserId(userId);

            resultMap.put(ME, addReviewsToReviewList(userReviewList));

            return resultMap;
        }

        // (타인의 리뷰를 보고싶은 경우 - 리뷰별 공개 여부 확인 필요)
        Optional<User> userTmp = userRepository.findById(userId);
        userTmp.ifPresent(user -> {
            if( user.getIsReviewOpen()) {
                List<Review> othersReviewList = reviewRepository.findAllByUserIdAndIsSecret(userId, false);
                resultMap.put(OPEN, addReviewsToReviewList(othersReviewList));
            }
            else {
                resultMap.put(PRIVATE, new ArrayList<>());
            }
        });

        return resultMap;
    }

    // 유저 해시태그 객체 목록에서 해시태그컨텐츠들만 가져옴
    public List<String> convertUserHashtagListToTagList(List<UserHashtag> userHashtags) {
        return userHashtags.stream().map(UserHashtag::getContent).collect(Collectors.toList());
    }

    // 검색어로 찾은 유저들에 대해 필요한 내용들을 리스트에 담아줌
    public void addUsersToUserList(List<MyFeedUserRes> userListArr, List<User> searchedUsers) {
        for(User user : searchedUsers) {
            List<String> hashtagList = convertUserHashtagListToTagList(userHashtagRepository.findByUserId(user.getId()));

            int len = hashtagList.size();
            for(int i = 0; i < 3-len; i++)
                hashtagList.add(" ");

            userListArr.add(
                    MyFeedUserRes.builder()
                            .userId(user.getId())
                            .imagePath(user.getImagePath())
                            .nickname(user.getNickname())
                            .userHashtag(hashtagList)
                            .build());
        }
    }

    // 서재에 있는 책들에 대해 필요한 내용들을 리스트에 담아줌
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

    // 리뷰목록에서 필요한 내용들만 가져와서 리스트에 담아줌
    public List<MyFeedReviewListRes> addReviewsToReviewList(List<Review> tmpReviewList) {
        List<MyFeedReviewListRes> reviewList = new ArrayList<>();

        for (Review review : tmpReviewList) {
            List<ReviewPhoto> reviewPhotoList = reviewPhotoRepository.findAllByReviewId(review.getId());
            List<String> photoUrlList;
            
            // 리뷰에 등록된 사진이 한 개도 없을 경우
            if(reviewPhotoList.size() == 0) {
                photoUrlList = new ArrayList<>();
                // 책 썸네일 대신 보내줌
                photoUrlList.add(review.getBook().getBookImgUrl());
            }
            // 등록된 사진 있을 경우
            else {
                // url만 뽑아내서 보내줌
                photoUrlList = convertReviewPhotoListToUrlList(reviewPhotoList);
            }

            reviewList.add(
                    MyFeedReviewListRes.builder()
                            .reviewId(review.getId())
                            .bookTitle(review.getBook().getBookTitle())
                            .title(review.getTitle())
                            .photoUrlList(photoUrlList)
                            .isSecret(review.getIsSecret())
                            .build());
        }
        
        return reviewList;
    }

    // 리뷰에 연결된 사진 객체들에서 url만 가져옴
    public List<String> convertReviewPhotoListToUrlList(List<ReviewPhoto> reviewPhoto){
        return reviewPhoto.stream().map(ReviewPhoto::getPhotoUrl).collect(Collectors.toList());
    }
}

