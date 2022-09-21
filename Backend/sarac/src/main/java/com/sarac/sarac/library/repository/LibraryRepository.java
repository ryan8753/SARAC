package com.sarac.sarac.library.repository;

import com.sarac.sarac.library.entity.Library;
import com.sarac.sarac.library.type.LibraryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    // 추후 wordcount 구현되면 top3만 뽑아내기
    Long countByUserIdAndLibraryType(Long userId, LibraryType libraryType);
}
