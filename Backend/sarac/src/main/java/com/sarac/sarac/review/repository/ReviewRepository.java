package com.sarac.sarac.review.repository;

import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.review.entity.BookScore;
import com.sarac.sarac.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByUserId(Long id);

    List<Review> findAllByUserIdAndIsSecret(Long id, Boolean isSecret);

    Review findOneById(Long id);

    List<Review> findAllByBookIsbnAndIsSecret(String isbn, Boolean isSecret);

    List<Review> findTop6ByBookOrderByIdDesc(Book book);

    // TODO: 2022-09-22  20개까지 limit걸 방법 생각해보기 
    @Query("select book.isbn from Review group by book.isbn order by count(book.isbn) desc")
    List<String> findHotBooks();

    List<Review> findByBook_IsbnIn(List<String> bookList);


    List<BookScore> findAllByBook(Book book);

    Boolean existsByBook(Book book);

    Boolean existsByUserId(Long userId);


    @Transactional
    void deleteReviewById(Long id);

}
