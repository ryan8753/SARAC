package com.sarac.sarac.statistics.service;


import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.book.repository.BookRepository;
import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.library.repository.LibraryRepository;
import com.sarac.sarac.review.entity.Review;
import com.sarac.sarac.review.repository.ReviewRepository;
import com.sarac.sarac.statistics.payload.response.StatisticsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import static com.sarac.sarac.library.type.LibraryType.READ;

@RequiredArgsConstructor
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final ReviewRepository reviewRepository;

    private final LibraryRepository libraryRepository;

    private final BookRepository bookRepository;

    private final int EMPTY = 0;


    @Override
    public StatisticsDTO getStatistics(Long userId) {

//        List<Book> bookList = libraryRepository.findAllByUserIdAndStatus(userId,READ).stream()
//                        .map(Library::getBook)

        StatisticsDTO statisticsDTO = StatisticsDTO.builder()
                .totalPrice(libraryRepository.existsByUserIdAndLibraryType(userId,READ) ?
                        libraryRepository.findAllByUserIdAndLibraryType(userId,READ).stream()
                                .map(Library::getBook).mapToInt(Book::getPrice).sum() : EMPTY)
                .totalScore(reviewRepository.existsByUserId(userId) ?
                        reviewRepository.findAllByUserId(userId).stream()
                                .mapToInt(Review::getBookScore).average().getAsDouble() : EMPTY)
                .totalNoise(0)
                .build();
        //소음총 작업후 추가

        return statisticsDTO;
    }
}
