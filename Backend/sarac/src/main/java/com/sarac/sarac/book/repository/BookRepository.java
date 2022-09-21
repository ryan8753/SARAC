package com.sarac.sarac.book.repository;

import com.sarac.sarac.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    Book findOneByIsbn(String isbn);

}
