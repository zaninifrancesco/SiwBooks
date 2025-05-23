package it.uniroma3.siw.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import it.uniroma3.siw.siwbooks.model.Libro;
import it.uniroma3.siw.siwbooks.model.Recensione;
import it.uniroma3.siw.siwbooks.model.Utente;
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

    /**
     * Mostra il form per aggiungere una nuova recensione.
     * [Questo metodo non è più utilizzato poiché il form è ora integrato nella pagina del libro]
     */
    @GetMapping("/libri/{libroId}/recensioni/new")
    public String newRecensioneForm(@PathVariable("libroId") Long libroId, RedirectAttributes redirectAttributes) {
        // Reindirizza alla pagina del libro, che ora contiene il form
        return "redirect:/libri/" + libroId;
    
    }

    /**
     * Salva una nuova recensione associandola all'utente corrente e al libro.
     */
    @PostMapping("/libri/{libroId}/recensioni")
    public String saveRecensione(@PathVariable("libroId") Long libroId, 
                               @Valid @ModelAttribute("recensione") Recensione recensione, 
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        // Recupera il libro e l'utente
        Libro libro = libroService.findById(libroId);
        Utente utenteCorrente = utenteService.getCurrentUser();
        
        // Verifica che il libro esista
        if (libro == null) {
            return "redirect:/libri";
        }
        
        // Verifica errori di validazione
        if (bindingResult.hasErrors()) {
            model.addAttribute("libro", libro);
            return "formRecensione";
        }
        
        // Verifica che l'utente non abbia già recensito questo libro
        if (recensioneService.existsRecensioneByUtenteAndLibro(utenteCorrente, libro)) {
            redirectAttributes.addFlashAttribute("erroreRecensione", 
                "Hai già scritto una recensione per questo libro.");
            return "redirect:/libri/" + libroId;
        }
        
        // Imposta libro e utente per la recensione
        recensione.setLibro(libro);
        recensione.setUtente(utenteCorrente);
        
        // Salva la recensione (il service imposterà dataCreazione)
        recensioneService.save(recensione);
        
        // Messaggio di conferma e redirect
        redirectAttributes.addFlashAttribute("successoRecensione", 
            "La tua recensione è stata pubblicata con successo!");
        return "redirect:/libri/" + libroId;
    }

    /**
     * Elimina una recensione (solo admin).
     */
    @GetMapping("/recensioni/{id}/delete")
    public String deleteRecensione(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        // Recupera la recensione
        Recensione recensione = recensioneService.findById(id);
        Long libroId = null;
        
        if (recensione != null && recensione.getLibro() != null) {
            libroId = recensione.getLibro().getId();
            recensioneService.deleteById(id);
            
            redirectAttributes.addFlashAttribute("successoOperazione", 
                "La recensione è stata eliminata con successo.");
                
            if (libroId != null) {
                 return "redirect:/libri/" + libroId;
            }
        }
        return "redirect:/libri";
    }
    
    /**
     * Mostra le recensioni dell'utente corrente.
     */
    @GetMapping("/utente/recensioni")
    public String recensioniUtente(Model model) {
        // Recupera l'utente corrente
        Utente utenteCorrente = utenteService.getCurrentUser();
        
        if (utenteCorrente != null) {
            // Recupera le recensioni dell'utente
            model.addAttribute("recensioni", recensioneService.getRecensioniUtente(utenteCorrente));
            model.addAttribute("utente", utenteCorrente);
            return "recensioniUtente";
        }
        
        return "redirect:/login";
    }
}