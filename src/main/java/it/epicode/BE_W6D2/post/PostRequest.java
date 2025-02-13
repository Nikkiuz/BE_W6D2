package it.epicode.BE_W6D2.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostRequest {

	@NotBlank(message = "La categoria non può essere vuota")
	private String categoria;

	@NotBlank(message = "Il titolo non può essere vuoto")
	private String titolo;

	private String cover;

	@NotBlank(message = "Il contenuto non può essere vuoto")
	private String contenuto;

	private int tempoDiLettura;

	@NotNull(message = "L'autoreId non può essere vuoto")
	private Long autoreId;

	@NotNull(message = "Il blogId non può essere vuoto")
	private Long blogId;
}
