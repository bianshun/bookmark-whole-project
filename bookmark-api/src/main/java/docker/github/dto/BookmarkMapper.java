package docker.github.dto;

import org.springframework.stereotype.Component;
import docker.github.domain.Bookmark;

@Component
public class BookmarkMapper {

    public BookmarkDTO toDTO(Bookmark bookmark){
        BookmarkDTO bookmarkDTO = new BookmarkDTO();
        bookmarkDTO.setId(bookmark.getId());
        bookmarkDTO.setTitle(bookmark.getTitle());
        bookmarkDTO.setUrl(bookmark.getUrl());
        bookmarkDTO.setCreatedAt(bookmark.getCreatedAt());
        return bookmarkDTO;
    }

    public Bookmark toData(BookmarkDTO bookmarkDTO){
        Bookmark bookmark = new Bookmark();
        bookmark.setId(bookmarkDTO.getId());
        bookmark.setTitle(bookmarkDTO.getTitle());
        bookmark.setUrl(bookmarkDTO.getUrl());
        bookmark.setCreatedAt(bookmarkDTO.getCreatedAt());
        return bookmark;
    }
}
