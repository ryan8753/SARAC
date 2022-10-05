package com.sarac.sarac.book.repository;

import com.sarac.sarac.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookSearchRepository extends JpaRepository<Book, String> {
    Page<Book> findByIsbnOrAuthorContainsOrBookTitleContains(String keyword1, String keyword2, String keyword3, Pageable pageable);

    @Query("SELECT book FROM Review review join review.book book group by book.isbn order by count(book.isbn) desc")
    List<Book> findBestBooks();
}
