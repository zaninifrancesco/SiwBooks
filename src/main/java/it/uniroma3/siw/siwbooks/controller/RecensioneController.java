package it.uniroma3.siw.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import it.uniroma3.siw.siwbooks.model.Recensione;
import it.uniroma3.siw.siwbooks.service.RecensioneService;
import it.uniroma3.siw.siwbooks.service.LibroService;
import it.uniroma3.siw.siwbooks.service.UtenteService;
// Importare il modello Utente e Libro se necessario per associare la recensione

@Controller
public class RecensioneController {

    @Autowired
    private RecensioneService recensioneService;

    @Autowired
    private LibroService libroService; // Per caricare il libro a cui aggiungere la recensione

    @Autowired
    private UtenteService utenteService; // Per associare l'utente corrente

    // Metodo per mostrare il form di aggiunta recensione (esempio)
    @GetMapping("/libri/{libroId}/recensioni/new")
    public String newRecensioneForm(@PathVariable("libroId") Long libroId, Model model) {
        // Logica per verificare che l'utente possa recensire questo libro
        // (es. non ha già recensito, il libro esiste)
        model.addAttribute("recensione", new Recensione());
        model.addAttribute("libroId", libroId);
        return "formRecensione"; // Vista Thymeleaf per il form
    }

    // Metodo per salvare una nuova recensione
    @PostMapping("/libri/{libroId}/recensioni")
    public String saveRecensione(@PathVariable("libroId") Long libroId, 
                               @ModelAttribute Recensione recensione, Model model) {
        // Logica per associare il libro e l'utente alla recensione
        // Esempio: recensione.setLibro(libroService.findById(libroId));
        // Esempio: recensione.setUtente(utenteService.getCurrentUser()); // Metodo da implementare
        recensioneService.save(recensione);
        return "redirect:/libri/" + libroId;
    }

    // Metodo per cancellare una recensione (solo admin)
    @GetMapping("/recensioni/{id}/delete") // O PostMapping a seconda delle preferenze
    public String deleteRecensione(@PathVariable("id") Long id, Model model) {
        // Logica per verificare i permessi dell'admin
        Recensione recensione = recensioneService.findById(id);
        Long libroId = null;
        if (recensione != null && recensione.getLibro() != null) {
            libroId = recensione.getLibro().getId();
            recensioneService.deleteById(id);
            if (libroId != null) {
                 return "redirect:/libri/" + libroId;
            }
        }
        return "redirect:/libri"; // O altra pagina di fallback
    }
}