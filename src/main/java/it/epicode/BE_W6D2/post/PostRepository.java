package it.epicode.BE_W6D2.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
	public Post findByTitolo(String titolo);
	public static boolean existsByTitolo(String titolo);
	public Post findByContenuto(String contenuto);
	public static boolean existsByContenuto(String contenuto);
}
