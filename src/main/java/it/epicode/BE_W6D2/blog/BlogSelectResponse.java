package it.epicode.BE_W6D2.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogSelectResponse {
	private Long id;
	private String nome;
	private String indirizzoWeb;
}
