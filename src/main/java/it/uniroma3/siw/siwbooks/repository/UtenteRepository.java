package it.uniroma3.siw.siwbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.uniroma3.siw.siwbooks.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    // Eventuali metodi di query personalizzati possono essere aggiunti qui,
    // per esempio per trovare un utente per username:
    public Utente findByUsername(String username);
}
