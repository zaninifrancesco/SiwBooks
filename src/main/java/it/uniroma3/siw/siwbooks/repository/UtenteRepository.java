package it.uniroma3.siw.siwbooks.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.siwbooks.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {
    // Eventuali metodi di query personalizzati possono essere aggiunti qui,
    // per esempio per trovare un utente per username:
    public Utente findByUsername(String username);
}
