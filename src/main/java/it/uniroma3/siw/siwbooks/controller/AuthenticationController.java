package it.uniroma3.siw.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.siw.siwbooks.model.Utente;
import it.uniroma3.siw.siwbooks.service.UtenteService;
import it.uniroma3.siw.siwbooks.service.LibroService; // Aggiunto import
import it.uniroma3.siw.siwbooks.service.AutoreService; // Aggiunto import
import it.uniroma3.siw.siwbooks.model.Ruolo;
import it.uniroma3.siw.siwbooks.model.Libro;
import it.uniroma3.siw.siwbooks.model.Autore;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthenticationController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private LibroService libroService; // Aggiunta dipendenza

    @Autowired
    private AutoreService autoreService; // Aggiunta dipendenza

    @GetMapping("/") // Metodo per la homepage
    public String index(Model model) {
        // Limita a 6 libri e 6 autori per la homepage
        Iterable<Libro> allLibri = libroService.findAll();
        Iterable<Autore> allAutori = autoreService.findAll();
        
        List<Libro> libriLimitati = new ArrayList<>();
        int contLibri = 0;
        for(Libro l : allLibri) {
            if(contLibri >= 6) break;
            libriLimitati.add(l);
            contLibri++;
        }
        
        List<Autore> autoriLimitati = new ArrayList<>();
        int contAutori = 0;
        for(Autore a : allAutori) {
            if(contAutori >= 6) break;
            autoriLimitati.add(a);
            contAutori++;
        }
        
        model.addAttribute("libri", libriLimitati);
        model.addAttribute("autori", autoriLimitati);
        model.addAttribute("ciSonoAltriLibri", contLibri == 6);
        model.addAttribute("ciSonoAltriAutori", contAutori == 6);
        return "index";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "login"; // Rimuovi .html, Spring MVC lo risolve automaticamente
    }

    // Spring Security gestirà il POST a /login e il logout

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("utente", new Utente());
        return "register"; // Rimuovi .html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Utente utente, Model model) {
        if (utenteService.findByUsername(utente.getUsername()) != null) {
            model.addAttribute("erroreUsername", "Username già esistente. Scegline un altro.");
            return "register"; // Torna al form di registrazione
        }
        utente.setRuolo(Ruolo.USER); // Assegna ruolo USER di default
        utenteService.save(utente); // Il service si occuperà della codifica della password
        return "redirect:/login?registrationSuccess";
    }
}