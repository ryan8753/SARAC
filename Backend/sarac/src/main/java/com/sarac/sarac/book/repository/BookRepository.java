package com.sarac.sarac.book.repository;

import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.statistics.payload.response.TopGenreDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    Optional<Book> findOneByIsbn(String isbn);

    @Query(value ="select book.genre,count(book.genre) as cnt from book  join library on library.isbn=book.isbn where library.library_type='READ' and library.user_Id=:userId group by book.genre order by count(book.genre) desc limit 3",nativeQuery = true)
    List<TopGenreDTO> findTopGenre(@Param("userId") Long userId);

}
