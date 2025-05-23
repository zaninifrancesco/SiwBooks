package it.uniroma3.siw.siwbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder; // Importa PasswordEncoder
import org.springframework.stereotype.Service;
import it.uniroma3.siw.siwbooks.model.Utente;
import it.uniroma3.siw.siwbooks.repository.UtenteRepository;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inietta PasswordEncoder

    public Utente findById(Long id) {
        return utenteRepository.findById(id).orElse(null);
    }

    public Iterable<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public Utente save(Utente utente) {
        // Codifica la password prima di salvare l'utente
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepository.save(utente);
    }

    // Metodo per trovare l'utente per username, utile per la logica di registrazione e login
    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }
    
    /**
     * Ottiene l'utente correntemente autenticato.
     * @return l'utente corrente, o null se non c'è nessun utente autenticato
     */
    public Utente getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String username = authentication.getName();
        return findByUsername(username);
    }
}
