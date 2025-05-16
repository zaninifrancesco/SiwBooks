package it.uniroma3.siw.siwbooks.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.siwbooks.model.Autore;

public interface AutoreRepository extends CrudRepository<Autore, Long> {
    // Eventuali metodi di query personalizzati possono essere aggiunti qui
}
