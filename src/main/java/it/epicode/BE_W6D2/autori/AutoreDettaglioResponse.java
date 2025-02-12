package it.epicode.BE_W6D2.autori;

import it.epicode.BE_W6D2.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoreDettaglioResponse {
	private Long id;
	private String nome;
	private String cognome;
	private String email;
	private LocalDate dataDiNascita;
	private String avatar;
	private Long blogId;
	private Set<Post> post;
}
