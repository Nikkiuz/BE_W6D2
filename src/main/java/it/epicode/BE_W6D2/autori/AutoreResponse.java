package it.epicode.BE_W6D2.autori;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoreResponse {

	private Long id;
	private String nome;
	private String cognome;
	private String email;
}
