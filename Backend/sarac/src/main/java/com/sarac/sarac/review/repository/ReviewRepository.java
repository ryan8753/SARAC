package com.sarac.sarac.review.repository;

import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review save(Review review);

    List<Review> findAllByUserId(Long id);

    Review findOneById(Long id);

    List<Review> findAllByBookIsbn(String isbn);

    List<Review> findTop4ByBookOrderByIdDesc(Book book);

    // TODO: 2022-09-22  20개까지 limit걸 방법 생각해보기 
    @Query("select book.isbn from Review group by book.isbn order by count(book.isbn) desc")
    List<String> findHotBooks();

    List<Review> findByBook_IsbnIn(List<String> bookList);



    @Transactional
    void deleteReviewById(Long id);



}
