package com.sarac.sarac.review.repository;

import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.review.entity.BookScore;
import com.sarac.sarac.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByUserId(Long id);

    Review findOneById(Long id);

    List<Review> findAllByBookIsbn(String isbn);

    List<BookScore> findAllByBook(Book book);

    Boolean existsByBook(Book book);

    @Transactional
    void deleteReviewById(Long id);

}
