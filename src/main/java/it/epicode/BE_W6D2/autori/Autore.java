package it.epicode.BE_W6D2.autori;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.BE_W6D2.blog.Blog;
import it.epicode.BE_W6D2.post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "autori")

public class Autore {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nome;
	private String cognome;
	@Column(unique = true)
	private String email;
	private LocalDate dataDiNascita;
	private String avatar;

	@ManyToOne
		@ToString.Exclude
		@JsonIgnoreProperties("autori")
	Blog blog;

	@ToString.Exclude
	@JsonIgnoreProperties("autore")
	@OneToMany(mappedBy = "autore")
	private Set<Post> posts = new HashSet<>();
}
