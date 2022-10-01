package com.sarac.sarac.book.repository;

import com.sarac.sarac.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    Optional<Book> findOneByIsbn(String isbn);

}
