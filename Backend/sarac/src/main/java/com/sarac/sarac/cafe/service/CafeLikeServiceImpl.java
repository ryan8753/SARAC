package com.sarac.sarac.cafe.service;

import com.sarac.sarac.cafe.dto.request.CafeLikeRequest;
import com.sarac.sarac.cafe.entity.Cafe;
import com.sarac.sarac.cafe.entity.CafeLike;
import com.sarac.sarac.cafe.exception.CafeException;
import com.sarac.sarac.cafe.exception.CafeLikeException;
import com.sarac.sarac.cafe.repository.CafeLikeRepository;
import com.sarac.sarac.cafe.repository.CafeRepository;
import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.exception.UserException;
import com.sarac.sarac.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CafeLikeServiceImpl implements CafeLikeService {

    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;
    private final CafeLikeRepository cafeLikeRepository;

    @Override
    @Transactional
    public void setGoodOrBadInfo(CafeLikeRequest.CafeLikeInfo cafeLikeInfo) {
        Cafe cafe = cafeRepository.findById(cafeLikeInfo.getCafe().getId()).orElseThrow(CafeException.CafeNotFoundException::new);
        User user = userRepository.findOneById(cafeLikeInfo.getUser().getId());
        // User Repository의 findOneById()를 Optional<User>로 바꾸면 orElseThrow() 구문으로 변경 가능
        if(user == null) throw new UserException.UserNotFoundException();
        boolean goodOrBad = cafeLikeInfo.isGoodOrBad();

        CafeLike cafeLike = cafeLikeRepository.findOneByCafeAndUser(cafe, user)
                .orElse(null);

        if(cafeLike == null) {
            cafeLike = CafeLike.builder()
                    .cafe(cafe)
                    .user(user)
                    .goodOrBad(goodOrBad)
                    .build();
        }

        cafeLike.setGoodOrBad(goodOrBad);
        cafeLikeRepository.save(cafeLike);
    }

    @Override
    @Transactional
    public void removeGoodOrBadInfo(CafeLikeRequest.CafeLikeInfo cafeLikeInfo) {
        Cafe cafe = cafeRepository.findById(cafeLikeInfo.getCafe().getId()).orElseThrow(CafeException.CafeNotFoundException::new);
        User user = userRepository.findOneById(cafeLikeInfo.getUser().getId());
        // User Repository의 findOneById()를 Optional<User>로 바꾸면 orElseThrow() 구문으로 변경 가능
        if(user == null) throw new UserException.UserNotFoundException();

        CafeLike cafeLike = cafeLikeRepository.findOneByCafeAndUser(cafe, user)
                .orElseThrow(CafeLikeException.NoSuchCafeLikeException::new);

        cafeLikeRepository.deleteByCafeAndUser(cafe, user);
    }

    @Override
    public Boolean getGoodOrBadInfo(CafeLikeRequest.CafeLikeStatus cafeLikeStatus) {
        Cafe cafe = cafeRepository.findById(cafeLikeStatus.getCafe().getId()).orElseThrow(CafeException.CafeNotFoundException::new);
        User user = userRepository.findOneById(cafeLikeStatus.getUser().getId());
        // User Repository의 findOneById()를 Optional<User>로 바꾸면 orElseThrow() 구문으로 변경 가능
        if(user == null) throw new UserException.UserNotFoundException();

        CafeLike cafeLike = cafeLikeRepository.findOneByCafeAndUser(cafe, user)
                .orElse(null);

        if(cafeLike == null) return null;
        else return cafeLike.isGoodOrBad();
    }
}
