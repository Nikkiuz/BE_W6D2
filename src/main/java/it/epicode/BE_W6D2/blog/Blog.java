package it.epicode.BE_W6D2.blog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blogs")
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nome;
	private String indirizzoWeb;

	@OneToMany
	private Set<Autore> autori = new HashSet<>();
	@OneToMany
	private Set<Post> posts = new HashSet<>();
}
