package com.sarac.sarac.library.repository;

import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.library.type.LibraryType;
import com.sarac.sarac.user.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    // 추후 wordcount 구현되면 top3만 뽑아내기
    Long countByUserIdAndLibraryType(Long userId, LibraryType libraryType);

    public List<Library> findAllByUserAndLibraryType(User user, LibraryType libraryType);

    public List<Library> findByUserId(Long userId);

    Library findByUserIdAndBookIsbn(Long userId, String isbn);

    Boolean existsByUserIdAndLibraryType(Long userId,LibraryType libraryType);

    List<Library> findAllByUserIdAndLibraryType(Long userId,LibraryType libraryType);

    @Transactional
    void deleteByUserIdAndBookIsbn(Long userId, String isbn);
}
