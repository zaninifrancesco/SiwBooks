package it.uniroma3.siw.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import it.uniroma3.siw.siwbooks.service.LibroService;
import it.uniroma3.siw.siwbooks.service.RecensioneService;
import it.uniroma3.siw.siwbooks.service.UtenteService;
import it.uniroma3.siw.siwbooks.model.Libro;
import it.uniroma3.siw.siwbooks.model.Recensione;
import it.uniroma3.siw.siwbooks.model.Utente;

@Controller
public class LibroController {

    @Autowired
    private LibroService libroService;
    
    @Autowired
    private RecensioneService recensioneService;
    
    @Autowired
    private UtenteService utenteService;

    @GetMapping("/libri")
    public String getLibri(Model model) {
        model.addAttribute("libri", libroService.findAll());
        return "libri"; // Nome della vista Thymeleaf (es. libri.html)
    }

    @GetMapping("/libri/{id}")
    public String getLibro(@PathVariable("id") Long id, Model model) {
        Libro libro = libroService.findById(id);
        if (libro != null) {
            model.addAttribute("libro", libro);
            
            // Aggiungiamo le recensioni ordinate per data (più recenti prima)
            model.addAttribute("recensioni", recensioneService.getRecensioniLibro(libro));
            
            // Verifichiamo se l'utente corrente ha già scritto una recensione per questo libro
            Utente utenteCorrente = utenteService.getCurrentUser();
            if (utenteCorrente != null) {
                boolean haRecensito = recensioneService.existsRecensioneByUtenteAndLibro(utenteCorrente, libro);
                model.addAttribute("utenteHaRecensito", haRecensito);
                
                // Se l'utente non ha già recensito, aggiungiamo un oggetto recensione vuoto per il form
                if (!haRecensito) {
                    model.addAttribute("recensione", new Recensione());
                }
            }
            
            return "libro"; // Nome della vista Thymeleaf (es. libro.html)
        }
        return "redirect:/libri";
    }

    // Altri metodi per gestire la creazione, aggiornamento, cancellazione dei libri
    // verranno aggiunti in base alle funzionalità richieste per utenti registrati/admin
}