package it.uniroma3.siw.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import it.uniroma3.siw.siwbooks.service.AutoreService;
import it.uniroma3.siw.siwbooks.model.Autore;

@Controller
@RequestMapping("/autori")
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @GetMapping("")
    public String getAutori(Model model) {
        model.addAttribute("autori", autoreService.findAll());
        return "autori"; // Nome della vista Thymeleaf (es. autori.html)
    }

    @GetMapping("/{id}")
    public String getAutore(@PathVariable("id") Long id, Model model) {
        Autore autore = autoreService.findById(id);
        if (autore != null) {
            model.addAttribute("autore", autore);
            // Assicurati di avere una vista autore.html per i dettagli del singolo autore
            return "autore"; 
        }
        return "redirect:/autori";
    }

    // Altri metodi per gestire la creazione, aggiornamento, cancellazione degli autori
    // verranno aggiunti in base alle funzionalità richieste per utenti registrati/admin
}