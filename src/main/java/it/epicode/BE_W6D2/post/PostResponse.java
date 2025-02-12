package it.epicode.BE_W6D2.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
	public Long id;
	public String titolo;
	public String testo;
	public String categoria;
	public String cover;
	public int tempoDiLettura;
	private Long autoreId;
}
