package it.uniroma3.siw.siwbooks.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.siwbooks.model.Libro;
import it.uniroma3.siw.siwbooks.model.Recensione;
import it.uniroma3.siw.siwbooks.model.Utente;
import it.uniroma3.siw.siwbooks.repository.RecensioneRepository;

@Service
public class RecensioneService {

    @Autowired
    private RecensioneRepository recensioneRepository;

    public Iterable<Recensione> findAll() {
        return recensioneRepository.findAll();
    }

    public Recensione findById(Long id) {
        return recensioneRepository.findById(id).orElse(null);
    }
    
    /**
     * Verifica se l'utente ha già recensito il libro specificato.
     * @param utente l'utente
     * @param libro il libro
     * @return true se l'utente ha già recensito il libro, false altrimenti
     */
    public boolean existsRecensioneByUtenteAndLibro(Utente utente, Libro libro) {
        return recensioneRepository.existsByUtenteAndLibro(utente, libro);
    }
    
    /**
     * Trova la recensione di un utente per un libro specifico.
     * @param utente l'utente
     * @param libro il libro
     * @return la recensione, o null se non esiste
     */
    public Recensione findByUtenteAndLibro(Utente utente, Libro libro) {
        return recensioneRepository.findByUtenteAndLibro(utente, libro);
    }
    
    /**
     * Trova tutte le recensioni di un utente.
     * @param utente l'utente di cui cercare le recensioni
     * @return lista di recensioni dell'utente
     */
    public List<Recensione> getRecensioniUtente(Utente utente) {
        return recensioneRepository.findByUtente(utente);
    }
    
    /**
     * Trova tutte le recensioni per un libro, ordinate dalla più recente.
     * @param libro il libro di cui cercare le recensioni
     * @return lista di recensioni ordinate per data
     */
    public List<Recensione> getRecensioniLibro(Libro libro) {
        return recensioneRepository.findByLibroOrderByDataCreazioneDesc(libro);
    }

    /**
     * Salva una recensione impostando la data di creazione.
     * @param recensione la recensione da salvare
     * @return la recensione salvata
     */
    @Transactional
    public Recensione save(Recensione recensione) {
        // Imposta la data di creazione se è una nuova recensione
        if (recensione.getId() == null) {
            recensione.setDataCreazione(LocalDateTime.now());
        }
        return recensioneRepository.save(recensione);
    }

    /**
     * Elimina una recensione per ID.
     * @param id l'ID della recensione da eliminare
     */
    public void deleteById(Long id) {
        recensioneRepository.deleteById(id);
    }
}
