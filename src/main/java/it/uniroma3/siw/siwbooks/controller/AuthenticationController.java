package it.uniroma3.siw.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.siw.siwbooks.model.Utente;
import it.uniroma3.siw.siwbooks.service.UtenteService;
import it.uniroma3.siw.siwbooks.model.Ruolo; // Aggiungi import per Ruolo

@Controller
public class AuthenticationController {

    @Autowired
    private UtenteService utenteService;

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
        // Aggiungi un messaggio di successo opzionale per la pagina di login
        return "redirect:/login?registrationSuccess";
    }
}