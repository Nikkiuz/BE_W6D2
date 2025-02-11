package it.epicode.BE_W6D2.post;

import it.epicode.BE_W6D2.responses.CreateResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	public List<Post> findAll() {
		return postRepository.findAll();
	}

	public Post modify(Long id, PostRequest request) {
		Post post = findById(id);
		BeanUtils.copyProperties(request, post);
		postRepository.save(post);
		return post;
	}

	public CreateResponse save(PostRequest request) {
		if (postRepository.existsByTitolo(request.getTitolo())) {
			throw new EntityExistsException("Post already exists: title checked");
		}

		if (postRepository.existsByContenuto(request.getContenuto())) {
			throw new EntityExistsException("Post already exists: content checked");
		}

		Post post = new Post();
		BeanUtils.copyProperties(request, post);
		postRepository.save(post);

		CreateResponse response = new CreateResponse();
		BeanUtils.copyProperties(post, response);

		return response;
	}


	public Post findById(Long id) {
		if(!postRepository.existsById(id)) {
			throw new EntityNotFoundException("Post not found");
		}
		return postRepository.findById(id).get();
	}

	public void delete(Long id) {
		Post post = findById(id);
		postRepository.deleteById(id);
	}

}
