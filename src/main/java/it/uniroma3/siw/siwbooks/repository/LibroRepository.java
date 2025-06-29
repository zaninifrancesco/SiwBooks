package it.uniroma3.siw.siwbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.siwbooks.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Eventuali metodi di query personalizzati possono essere aggiunti qui
}
