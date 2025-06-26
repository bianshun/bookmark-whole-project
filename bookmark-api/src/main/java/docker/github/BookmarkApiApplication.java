package docker.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookmarkApiApplication {

/*	git init
	git add README.md
	git commit -m "first commit"
	git branch -M main
	git remote add origin https://github.com/bianshun/docker-github-test.git
	git push -u origin main*/

	public static void main(String[] args) {
		SpringApplication.run(BookmarkApiApplication.class, args);
	}

}
