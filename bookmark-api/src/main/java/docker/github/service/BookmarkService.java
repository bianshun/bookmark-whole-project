package docker.github.service;

import docker.github.domain.Bookmark;
import docker.github.dto.BookmarkMapper;
import docker.github.dto.CreateBookmarkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import docker.github.dto.BookmarkDTO;
import docker.github.dto.BookmarksDTO;
import docker.github.repository.BookmarkRepository;

import java.time.Instant;


@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkService {

    private final BookmarkRepository repository;
    private final BookmarkMapper bookmarkMapper;

    @Transactional(readOnly = true)
    public BookmarksDTO getBookmarksByPage(Integer page){
        int pageNo = page < 1 ? 0 : page -1;
        int maxNoPerPage = 10;
        Pageable pageable = PageRequest.of(pageNo, maxNoPerPage, Sort.Direction.ASC, "createdAt");
        Page<BookmarkDTO> bookmarkPage = repository.findBookmark(pageable);
        return new BookmarksDTO(bookmarkPage);
    }

    @Transactional(readOnly = true)
    public BookmarksDTO getBookmarksByQuery(String query, Integer page) {
        int pageNo = page < 1 ? 0 : page -1;
        int maxNoPerPage = 10;
        Pageable pageable = PageRequest.of(pageNo, maxNoPerPage, Sort.Direction.ASC, "createdAt");
        Page<BookmarkDTO> bookmarkPage = repository.findBookmarkByQuery(query, pageable);
        return new BookmarksDTO(bookmarkPage);
    }

    public BookmarkDTO createBookmark(CreateBookmarkRequest request){
        Bookmark savedBookmark = repository.save(new Bookmark(
                null,
                request.getTitle(),
                request.getUrl(),
                Instant.now()));
        return bookmarkMapper.toDTO(savedBookmark);
    }
}
