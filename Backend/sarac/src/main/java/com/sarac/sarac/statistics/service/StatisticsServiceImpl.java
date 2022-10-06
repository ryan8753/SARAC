package com.sarac.sarac.statistics.service;


import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.book.repository.BookRepository;
import com.sarac.sarac.cafe.repository.CafeNoiseRepository;
import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.library.repository.LibraryRepository;
import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.payload.response.ReviewHashtagDTO;
import com.sarac.sarac.review.repository.ReviewHashtagRepository;
import com.sarac.sarac.review.repository.ReviewRepository;
import com.sarac.sarac.statistics.payload.response.StatisticsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.sarac.sarac.library.type.LibraryType.READ;

@RequiredArgsConstructor
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final ReviewRepository reviewRepository;

    private final LibraryRepository libraryRepository;

    private final BookRepository bookRepository;

    private final ReviewHashtagRepository reviewHashtagRepository;

    private final CafeNoiseRepository cafeNoiseRepository;

    private final int EMPTY = 0;


    @Override
    public StatisticsDTO getStatistics(Long userId) {

//        List<Book> bookList = libraryRepository.findAllByUserIdAndStatus(userId,READ).stream()
//                        .map(Library::getBook)

        StatisticsDTO statisticsDTO = StatisticsDTO.builder()
                .topGenre(bookRepository.findTopGenre(userId))
                .totalPage(libraryRepository.existsByUserIdAndLibraryType(userId,READ) ?
                        libraryRepository.findAllByUserIdAndLibraryType(userId,READ).stream()
                                .map(Library::getBook).mapToInt(Book::getPage).sum() : EMPTY)
                .totalPrice(libraryRepository.existsByUserIdAndLibraryType(userId,READ) ?
                        libraryRepository.findAllByUserIdAndLibraryType(userId,READ).stream()
                                .map(Library::getBook).mapToInt(Book::getPrice).sum() : EMPTY)
                .totalScore(reviewRepository.existsByUserId(userId) ?
                        reviewRepository.findAllByUserId(userId).stream()
                                .mapToInt(Review::getBookScore).average().getAsDouble() : EMPTY)
                .totalNoise(cafeNoiseRepository.countByUserId(userId))
                .build();


        return statisticsDTO;
    }

    @Override
    public List<ReviewHashtagDTO> getHashtags(Long userId) {

        List<ReviewHashtagDTO> reviewHashtagDTOS =reviewHashtagRepository.findHashtagCnt(userId);
        Collections.sort(reviewHashtagDTOS, (o1, o2) -> -(o1.getValue() - o2.getValue()));
        return reviewHashtagDTOS.stream().limit(100).collect(Collectors.toList());


    }
}
