package docker.github.api;

import docker.github.domain.Bookmark;
import docker.github.dto.CreateBookmarkRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import docker.github.dto.BookmarksDTO;
import docker.github.service.BookmarkService;

import java.time.Instant;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class Controller {

    private final BookmarkService service;

    @GetMapping()
    public BookmarksDTO getBookmarksByPage(@RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "query", defaultValue = "") String query){
        if(query == null || query.trim().length() == 0) {
            return service.getBookmarksByPage(pageNo);
        }
        return service.getBookmarksByQuery(query, pageNo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBookmark(@RequestBody @Valid CreateBookmarkRequest request){
        service.createBookmark(request);
    }
}
