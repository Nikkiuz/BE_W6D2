package it.epicode.BE_W6D2.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {
	private final BlogService blogService;

	@GetMapping
	private Page<Blog> findAll(@RequestParam int page,@RequestParam int recordPerPagina, @RequestParam String sortBy){
		Pageable pageable = PageRequest.of(page, recordPerPagina, Sort.by(sortBy));
		return blogService.findAll(pageable);
	}
}
