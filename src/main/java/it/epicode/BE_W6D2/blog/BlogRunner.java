package it.epicode.BE_W6D2.blog;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Component
@RequiredArgsConstructor
public class BlogRunner implements CommandLineRunner {
	private final BlogRepository blogRepository;
	private final Faker faker;
	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Blog blog = new Blog();
			blog.setNome(faker.name().firstName());
			blog.setIndirizzoWeb(faker.internet().url());
			blogRepository.save(blog);
		}
	}
}
