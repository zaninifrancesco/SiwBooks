package it.uniroma3.siw.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import it.uniroma3.siw.siwbooks.service.LibroService;
import it.uniroma3.siw.siwbooks.model.Libro;

@Controller
public class LibroController {

    @Autowired
    private LibroService libroService;

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
            return "libro"; // Nome della vista Thymeleaf (es. libro.html)
        }
        return "redirect:/libri";
    }

    // Altri metodi per gestire la creazione, aggiornamento, cancellazione dei libri
    // verranno aggiunti in base alle funzionalità richieste per utenti registrati/admin
}