package docker.github.repository;

import docker.github.dto.CreateBookmarkRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import docker.github.domain.Bookmark;
import docker.github.dto.BookmarkDTO;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query("select new docker.github.dto.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from Bookmark b")
    Page<BookmarkDTO> findBookmark(Pageable pageable);

    @Query(
            """
            select new docker.github.dto.BookmarkDTO(b.id, b.title, b.url, b.createdAt) from Bookmark b
            where lower(b.title) like lower(concat('%', :query, '%'))
            """)
    Page<BookmarkDTO> findBookmarkByQuery(String query, Pageable pageable);
}
