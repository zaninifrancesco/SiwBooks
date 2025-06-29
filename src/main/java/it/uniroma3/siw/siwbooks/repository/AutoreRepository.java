package it.uniroma3.siw.siwbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.siwbooks.model.Autore;

public interface AutoreRepository extends JpaRepository<Autore, Long> {
    // Eventuali metodi di query personalizzati possono essere aggiunti qui
}
