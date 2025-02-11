package it.epicode.BE_W6D2.autori;

import it.epicode.BE_W6D2.responses.CreateResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoreService {
	private final AutoreRepository autoreRepository;

	public List<Autore> findAll() {
		return autoreRepository.findAll();
	}

	public Autore modify(Long id, AutoreRequest request) {
		Autore autore = findById(id);
		BeanUtils.copyProperties(request, autore);
		autoreRepository.save(autore);
		return autore;
	}

	public CreateResponse save(AutoreRequest request) {
		if(autoreRepository.existsByNomeAndCognome(request.getNome(), request.getCognome())) {
			throw new EntityExistsException("Autore already exists");
		}

		if (autoreRepository.existsByEmail(request.getEmail())) {
			throw new EntityExistsException("Autore already exists");
		}

		Autore autore = new Autore();
		BeanUtils.copyProperties(request, autore);
		autoreRepository.save(autore);

		CreateResponse response = new CreateResponse();
		BeanUtils.copyProperties(autore, response);

		return response;

	}


	public Autore findById(Long id) {
		if(!autoreRepository.existsById(id)) {
			throw new EntityNotFoundException("Autore not found");
		}
		return autoreRepository.findById(id).get();
	}

	public void delete(Long id) {
		Autore autore = findById(id);
		autoreRepository.deleteById(id);
	}
}
