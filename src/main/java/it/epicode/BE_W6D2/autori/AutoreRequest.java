package it.epicode.BE_W6D2.autori;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoreRequest {

	@NotBlank(message = "Il nome non può essere vuoto")
	private String nome;

	@NotBlank(message = "Il cognome non può essere vuoto")
	private String cognome;

	@NotBlank(message = "L'email non può essere vuota")
	@Email(message = "L'email non è valida")
	private String email;

	@NotNull(message = "La data di nascita non può essere vuota")
	private LocalDate dataDiNascita;

	private String avatar;

	@NotNull(message = "Il blogId non può essere vuoto")
	private Long blogId;
}
