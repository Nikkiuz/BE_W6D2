package it.epicode.BE_W6D2.post;

import it.epicode.BE_W6D2.autori.Autore;
import it.epicode.BE_W6D2.autori.AutoreRepository;
import it.epicode.BE_W6D2.blog.Blog;
import it.epicode.BE_W6D2.blog.BlogRepository;
import it.epicode.BE_W6D2.responses.CreateResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class PostService {
	private final PostRepository postRepository;
	private final AutoreRepository autoreRepository;
	private final BlogRepository blogRepository;

	public List<PostResponse> findAll() {
		List<PostResponse> response = postResponseListFromEntityList(postRepository.findAll());
		return response;
	}

	public Post modify(@Valid Long id, PostRequest request) {
		Post post = findById(id);
		BeanUtils.copyProperties(request, post);
		postRepository.save(post);
		return post;
	}

	public CreateResponse save(@Valid PostRequest request){
	if(postRepository.existsByTitolo(request.getTitolo())){
		throw new EntityExistsException("Post già esistente");
	}

	if(!autoreRepository.existsById(request.getAutoreId())) {
		throw new EntityNotFoundException("Autore non trovato");
	}

	if(!blogRepository.existsById(request.getBlogId())) {
		throw new EntityNotFoundException("Blog non trovato");
	}

	Autore autore = autoreRepository.findById(request.getAutoreId()).get();

	Blog blog = blogRepository.findById(request.getBlogId()).get();

	Post post = postFromRequest(request);
	post.setAutore(autore);
		post.setBlog(blog);


	CreateResponse response = new CreateResponse();
	postRepository.save(post);
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