package it.epicode.BE_W6D2.autori;

import it.epicode.BE_W6D2.responses.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autori")
@RequiredArgsConstructor
public class AutoreController {
	private final AutoreService autoreService;


	//GET http://localhost:8080/api/autori
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AutoreResponse> findAll() {
		return autoreService.findAll();
	}

	//GET http://localhost:8080/api/autori/{id}
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Autore findById(@PathVariable Long id) {
		return autoreService.findById(id);
	}

	//POST http://localhost:8080/api/autori
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreateResponse save(@RequestBody AutoreRequest request) {
		return autoreService.save(request);
	}

	//PUT http://localhost:8080/api/autori/{id}
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Autore modify(@PathVariable Long id, @RequestBody AutoreRequest request) {
		return autoreService.modify(id, request);
	}

	//DELETE http://localhost:8080/api/autori/{id}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		autoreService.delete(id);
	}

}
