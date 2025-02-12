package it.epicode.BE_W6D2.post;


import it.epicode.BE_W6D2.responses.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	//GET http://localhost:8080/api/posts
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PostResponse> findAll() {
		return postService.findAll();
	}

	//GET http://localhost:8080/api/posts/{id}
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Post findById(@PathVariable Long id) {
		return postService.findById(id);
	}

	//POST http://localhost:8080/api/posts
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreateResponse save(@RequestBody PostRequest request) {
		return new CreateResponse(postService.save(request).getId());
	}

	//PUT http://localhost:8080/api/posts/{id}
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Post modify(@PathVariable Long id, @RequestBody PostRequest request) {
		return postService.modify(id, request);
	}

	//DELETE http://localhost:8080/api/posts/{id}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		postService.delete(id);
	}
}
