package it.epicode.BE_W6D2.autori;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoreRepository extends JpaRepository<Autore, Long> {
	public Autore findByEmail(String email);
	public boolean existsByEmail(String email);
	public Autore findByNomeAndCognome(String nome, String cognome);
	public boolean existsByNomeAndCognome(String nome, String cognome);
}
