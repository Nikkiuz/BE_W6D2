package it.epicode.BE_W6D2.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
	public Post findByTitolo(String titolo);
	public boolean existsByTitolo(String titolo);
	public Post findByContenuto(String contenuto);
	public boolean existsByContenuto(String contenuto);
	public boolean existsById(Long id);
	//public Post findById(Long id);

}
