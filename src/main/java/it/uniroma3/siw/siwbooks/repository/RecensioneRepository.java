package it.uniroma3.siw.siwbooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.siwbooks.model.Libro;
import it.uniroma3.siw.siwbooks.model.Recensione;
import it.uniroma3.siw.siwbooks.model.Utente;

public interface RecensioneRepository extends JpaRepository<Recensione, Long> {
    /**
     * Trova una recensione per utente e libro specifici.
     * @param utente l'utente che ha scritto la recensione
     * @param libro il libro recensito
     * @return la recensione se esiste, null altrimenti
     */
    Recensione findByUtenteAndLibro(Utente utente, Libro libro);
    
    /**
     * Verifica se esiste una recensione per un dato utente e libro.
     * @param utente l'utente che ha scritto la recensione
     * @param libro il libro recensito
     * @return true se esiste una recensione, false altrimenti
     */
    boolean existsByUtenteAndLibro(Utente utente, Libro libro);
    
    /**
     * Trova tutte le recensioni scritte da un utente specifico.
     * @param utente l'utente di cui cercare le recensioni
     * @return lista di recensioni dell'utente
     */
    List<Recensione> findByUtente(Utente utente);
    
    /**
     * Trova tutte le recensioni per un libro, ordinate per data di creazione discendente.
     * @param libro il libro di cui cercare le recensioni
     * @return lista di recensioni ordinate dalla più recente alla più vecchia
     */
    List<Recensione> findByLibroOrderByDataCreazioneDesc(Libro libro);
}
