package com.sarac.sarac.cafe.service;

import com.sarac.sarac.cafe.dto.request.CafeNoiseRequest;
import com.sarac.sarac.cafe.dto.response.CafeNoiseResponse;
import com.sarac.sarac.cafe.entity.Cafe;
import com.sarac.sarac.cafe.entity.CafeNoise;
import com.sarac.sarac.cafe.exception.CafeException;
import com.sarac.sarac.cafe.exception.CafeNoiseException;
import com.sarac.sarac.cafe.repository.CafeNoiseRepository;
import com.sarac.sarac.cafe.repository.CafeRepository;
import com.sarac.sarac.user.entitiy.User;
import com.sarac.sarac.user.exception.UserException;
import com.sarac.sarac.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CafeNoiseServiceImpl implements CafeNoiseService {

    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;
    private final CafeNoiseRepository cafeNoiseRepository;

    @Override
    @Transactional
    public void recordCafeNoise(CafeNoiseRequest.CafeNoiseInfo cafeNoiseInfo) {
        Cafe cafe = cafeRepository.findById(cafeNoiseInfo.getCafe().getId()).orElseThrow(CafeException.CafeNotFoundException::new);
        User user = userRepository.findOneById(cafeNoiseInfo.getUser().getId());
        // User Repository의 findOneById()를 Optional<User>로 바꾸면 orElseThrow() 구문으로 변경 가능
        if(user == null) throw new UserException.UserNotFoundException();
        double noise = cafeNoiseInfo.getNoise();

        CafeNoise cafeNoise = CafeNoise.builder()
                .cafe(cafe)
                .user(user)
                .noise(noise)
                .build();

        cafeNoiseRepository.save(cafeNoise);
    }

    @Override
    public CafeNoiseResponse.NoiseInfo getLastNoiseRecord(long cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId).orElseThrow(CafeException.CafeNotFoundException::new);
        CafeNoise lastRecordedCafeNoise = cafeNoiseRepository.findFirst1ByCafeOrderByCreatedAtDesc(cafe).orElseThrow(CafeNoiseException.CafeNoiseNotRecordedException::new);

        CafeNoiseResponse.NoiseInfo noiseInfo = CafeNoiseResponse.NoiseInfo.builder()
                .time(lastRecordedCafeNoise.getCreatedAt())
                .noise(lastRecordedCafeNoise.getNoise())
                .build();

        return noiseInfo;
    }
}
