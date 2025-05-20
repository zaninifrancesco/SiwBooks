package it.uniroma3.siw.siwbooks.service;

import it.uniroma3.siw.siwbooks.model.Utente;
import it.uniroma3.siw.siwbooks.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByUsername(username);
        if (utente == null) {
            throw new UsernameNotFoundException("Utente non trovato con username: " + username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (utente.getRuolo() != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + utente.getRuolo().name()));
        } else {
            // Se il ruolo è null, potresti voler lanciare un'eccezione o assegnare un ruolo di default
            // a seconda della logica della tua applicazione.
            // Per ora, lo lasciamo così, ma in produzione potrebbe essere un problema.
            // Considera di loggare un avviso o gestire questo caso più robustamente.
            System.out.println("Attenzione: l'utente " + username + " non ha un ruolo assegnato.");
        }


        return new org.springframework.security.core.userdetails.User(
                utente.getUsername(),
                utente.getPassword(),
                grantedAuthorities);
    }
}