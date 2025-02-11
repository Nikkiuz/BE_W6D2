package it.epicode.BE_W6D2.post;

import it.epicode.BE_W6D2.autori.Autore;
import it.epicode.BE_W6D2.blog.Blog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")

public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String categoria;
	private String titolo;
	private String cover;
	private String contenuto;
	private Time tempoDiLettura;

	@ManyToOne
	private Autore autore;
	@ManyToOne
	Blog blog;

}
