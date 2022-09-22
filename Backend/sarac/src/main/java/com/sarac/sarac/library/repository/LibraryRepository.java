package com.sarac.sarac.library.repository;

import com.sarac.sarac.book.entity.Book;
import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.library.type.LibraryType;
import com.sarac.sarac.user.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    public List<Library> findAllByUserAndLibraryType(User user, LibraryType libraryType);
}
