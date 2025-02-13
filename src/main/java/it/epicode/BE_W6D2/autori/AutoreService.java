package it.epicode.BE_W6D2.autori;

import it.epicode.BE_W6D2.blog.Blog;
import it.epicode.BE_W6D2.blog.BlogRepository;
import it.epicode.BE_W6D2.mail.EmailService;
import it.epicode.BE_W6D2.responses.CreateResponse;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class AutoreService {
	private final AutoreRepository autoreRepository;
	private final EmailService emailService;
	private final BlogRepository blogRepository;
	@Value("${messages.new.autore.subject}")
	private String newAutoreSubject;
	@Value("${messages.new.autore.body}")
	private String newAutoreBody;

	public List<AutoreResponse> findAll() {
		List<AutoreResponse> response = autoreResponseListFromEntityList(autoreRepository.findAll());
		return response;
	}

	public Autore modify(Long id, AutoreRequest request) {
		Autore autore = findById(id);
		BeanUtils.copyProperties(request, autore);
		autoreRepository.save(autore);
		return autore;
	}


	public CreateResponse save(@Valid AutoreRequest request) {
		if(autoreRepository.existsByEmail(request.getEmail())) {
			throw new EntityExistsException("Autore già esistente");
		}

		if (autoreRepository.existsByNomeAndCognome(request.getNome(), request.getCognome())) {
			throw new EntityExistsException("Autore already exists: controllo su nome e cognome");
	}
		if(!blogRepository.existsById(request.getBlogId())) {
			throw new EntityNotFoundException("Blog non trovato");
		}

		Autore autore = autoreFromRequest(request);
		Blog blog = blogRepository.findById(request.getBlogId()).get();
		autore.setBlog(blog);

		CreateResponse response = new CreateResponse();
		autoreRepository.save(autore);
		BeanUtils.copyProperties(autore, response);
	try {
		emailService.sendEmail("nick_albanese@virgilio.it", newAutoreSubject,
				newAutoreBody+autore.getNome()+" "+autore.getCognome());
	} catch (MessagingException e) {
		System.out.println("Errore invio email");

	}
		return response;
	}

	public Autore findById(Long id) {
		if (!autoreRepository.existsById(id)) {
			throw new EntityNotFoundException("Autore non trovato");
		}
		return autoreRepository.findById(id).get();
	}

	@Transactional
	public AutoreDettaglioResponse findAutoreResponseById(Long id) {
		if (!autoreRepository.existsById(id)) {
			throw new EntityNotFoundException("Autore non trovato");
		}

		Autore autore = autoreRepository.findById(id).get();

		AutoreDettaglioResponse response = new AutoreDettaglioResponse();
		BeanUtils.copyProperties(autore, response);
		response.setPost(autore.getPosts());
		response.setBlogId(autore.getBlog().getId());
		return response;
	}

	public void delete(Long id) {
		Autore autore = findById(id);
		autoreRepository.deleteById(id);
	}

	public AutoreResponse autoreResponseFromEntity(Autore autore){
		AutoreResponse response = new AutoreResponse();
		BeanUtils.copyProperties(autore, response);
		return response;
	}

	public List<AutoreResponse> autoreResponseListFromEntityList(List<Autore> autori){
		return autori.stream().map(this::autoreResponseFromEntity).toList();
	}

	public Autore autoreFromRequest(AutoreRequest request) {
		Autore autore = new Autore();
		BeanUtils.copyProperties(request, autore);
		return autore;
	}

}
