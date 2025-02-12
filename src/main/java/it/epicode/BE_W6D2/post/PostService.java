package it.epicode.BE_W6D2.post;

import it.epicode.BE_W6D2.responses.CreateResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	public List<PostResponse> findAll() {
		List<PostResponse> response = postResponseListFromEntityList(postRepository.findAll());
		return response;
	}

	public Post modify(Long id, PostRequest request) {
		Post post = findById(id);
		BeanUtils.copyProperties(request, post);
		postRepository.save(post);
		return post;
	}

	public CreateResponse save(PostRequest request){
	if(postRepository.existsByTitolo(request.getTitolo())){
		throw new EntityExistsException("Post già esistente");
	}

	if(postRepository.existsByContenuto(request.getContenuto())){
		throw new EntityExistsException("Post già esistente: controllo sul contenuto");
	}
	Post post = postFromRequest(request);
	CreateResponse response = new CreateResponse();
	BeanUtils.copyProperties(post,response);
	return response;
	}

	public Post findById(Long id){
		if(!postRepository.existsById(id)){
			throw new EntityNotFoundException("Post non trovato");
		}
		return postRepository.findById(id).get();
	}

	@Transactional
	public PostDettaglioResponse findPostResponseById(Long id){
		if(!postRepository.existsById(id)){
			throw new EntityNotFoundException("Post non trovato");
		}

		Post post = postRepository.findById(id).get();

		PostDettaglioResponse response = new PostDettaglioResponse();
		BeanUtils.copyProperties(post, response);
		response.setAutoreId(post.getAutore().getId());
		response.setBlogId(post.getBlog().getId());
		return response;
	}

	public void delete(Long id){
		Post post = findById(id);
		postRepository.deleteById(id);
	}

	public PostResponse postResponseFromEntity(Post post){
		PostResponse response = new PostResponse();
		BeanUtils.copyProperties(post, response);
		return response;
	}
	public List<PostResponse> postResponseListFromEntityList(List<Post> posts){
		return posts.stream().map(this::postResponseFromEntity).toList();
	}

	public Post postFromRequest(PostRequest request){
		Post post = new Post();
		BeanUtils.copyProperties(request, post);
		return post;
	}
}