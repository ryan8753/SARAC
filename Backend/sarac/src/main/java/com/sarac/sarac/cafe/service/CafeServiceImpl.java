package com.sarac.sarac.cafe.service;

import com.sarac.sarac.cafe.dto.request.CafeRequest;
import com.sarac.sarac.cafe.dto.response.CafeResponse;
import com.sarac.sarac.cafe.entity.Cafe;
import com.sarac.sarac.cafe.exception.CafeException;
import com.sarac.sarac.cafe.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CafeServiceImpl implements CafeService {

    private final CafeRepository cafeRepository;

    @Override
    public CafeResponse.CafeListInMapArea getCafeListInArea(CafeRequest.CafeListMapArea mapArea) {
        List<Cafe> cafeList = cafeRepository.findAllByLatitudeGreaterThanEqualAndLatitudeLessThanEqualAndLongitudeGreaterThanEqualAndLongitudeLessThanEqual(mapArea.getMinLatitude(), mapArea.getMaxLatitude(), mapArea.getMinLongitude(), mapArea.getMaxLongitude());

        return CafeResponse.CafeListInMapArea.builder().cafeList(cafeList).build();
    }

    @Override
    public CafeResponse.CafeInfo getCafeInfo(long cafeId) throws CafeException.CafeNotFoundException {
        Cafe cafe = cafeRepository.findById(cafeId).orElseThrow(CafeException.CafeNotFoundException::new);

        return CafeResponse.CafeInfo.builder().cafe(cafe).build();
    }
}
