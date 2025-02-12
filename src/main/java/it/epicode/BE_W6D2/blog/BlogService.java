package it.epicode.BE_W6D2.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {
	private final BlogRepository blogRepository;

	public Page<Blog> findAll(Pageable pageable) {
		return blogRepository.findAll(pageable);
	}
}
