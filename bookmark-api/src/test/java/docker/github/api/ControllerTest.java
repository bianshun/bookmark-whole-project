package docker.github.api;

import docker.github.domain.Bookmark;
import docker.github.repository.BookmarkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.org.hamcrest.CoreMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///test-db"
})
public class ControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    BookmarkRepository bookmarkRepository;

    private List<Bookmark> bookmarks;

    @BeforeEach
    void setUp(){
        bookmarkRepository.deleteAllInBatch();
        bookmarks = new ArrayList<>();
        bookmarks.add(new Bookmark(null, "shunbian", "https://shunbian.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yucheng", "https://yucheng.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yuming", "https://yuming.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "trump", "https://trump.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "shunbian", "https://shunbian.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yucheng", "https://yucheng.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yuming", "https://yuming.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "trump", "https://trump.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "shunbian", "https://shunbian.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yucheng", "https://yucheng.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yuming", "https://yuming.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "trump", "https://trump.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "shunbian", "https://shunbian.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yucheng", "https://yucheng.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yuming", "https://yuming.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "trump", "https://trump.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "shunbian", "https://shunbian.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yucheng", "https://yucheng.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yuming", "https://yuming.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "trump", "https://trump.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "shunbian", "https://shunbian.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yucheng", "https://yucheng.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yuming", "https://yuming.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "trump", "https://trump.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "shunbian", "https://shunbian.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yucheng", "https://yucheng.co.uk", Instant.now()));
        bookmarks.add(new Bookmark(null, "yuming", "https://yuming.com", Instant.now()));
        bookmarks.add(new Bookmark(null, "trump", "https://trump.com", Instant.now()));

        bookmarkRepository.saveAll(bookmarks);
    }

    @Test
    void getBookmarksByPageTest() throws Exception {
        mvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(10))
                .andExpect(jsonPath("$.totalPages").value(3))
                .andExpect(jsonPath("$.currentPage").value(1))
                .andExpect(jsonPath("$.isFirst").value(true))
                .andExpect(jsonPath("$.isLast").value(false))
                .andExpect(jsonPath("$.hasNext").value(true))
                .andExpect(jsonPath("$.hasPrevious").value(false))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        ;
    }

    @ParameterizedTest
    @CsvSource({
            "1, 10, 3, 1, true, false, true, false, application/json",
            "2, 10, 3, 2, false, false, true, true, application/json",
            "3, 8, 3, 3, false, true, false, true, application/json"
    })
    void getBookmarksByPageTestCsv(int pageNo, int totalElements, int totalPages, int currentPage, boolean isFirst,
                                   boolean isLast, boolean hasNext, boolean hasPrevious, String type) throws Exception {
        mvc.perform(get("/api?page=" + pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(totalElements))
                .andExpect(jsonPath("$.totalPages").value(totalPages))
                .andExpect(jsonPath("$.currentPage").value(currentPage))
                .andExpect(jsonPath("$.isFirst").value(isFirst))
                .andExpect(jsonPath("$.isLast").value(isLast))
                .andExpect(jsonPath("$.hasNext").value(hasNext))
                .andExpect(jsonPath("$.hasPrevious").value(hasPrevious))
                .andExpect(MockMvcResultMatchers.content().contentType(type))
        ;
    }

    @Test
    void shouldCreateBookmarkOk() throws Exception {
        mvc.perform(
                post("/api").contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """                                 
                                   {
                                              "title": "Wenxue City",
                                              "url": "http://www.wenxuecity.com"
                                    }
                                 """
                        )
        )
                .andExpect(status().isCreated());
                //.andExpect(jsonPath("$.title", is("Wenxue City")))
                //.andExpect(jsonPath("$.url", is("http://www.wenxuecity.com")));
    }

    @Test
    void shouldFailCreateBookmark() throws Exception {
        mvc.perform(
                        post("/api").contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        """                                 
                                           {
                                                      "title": "Wenxue City"
                                            }
                                         """
                                )
                )
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", is("application/problem+json")))
                .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.title", is("Constraint Violation")))
                .andExpect(jsonPath("$.violations", hasSize(1)))
                .andExpect(jsonPath("$.violations[0].field", is("url")))
                .andExpect(jsonPath("$.violations[0].message", is("Url should not be empty.")));
    }
}
