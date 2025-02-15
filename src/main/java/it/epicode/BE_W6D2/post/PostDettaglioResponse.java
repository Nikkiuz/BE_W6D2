package it.epicode.BE_W6D2.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDettaglioResponse {
	private Long id;
	private String titolo;
	private String contenuto;
	private String categoria;
	private String cover;
	private int tempoDiLettura;
	private Long autoreId;
	private Long blogId;
}
