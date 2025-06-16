package it.uniroma3.siw.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.siwbooks.model.Autore;
import it.uniroma3.siw.siwbooks.model.Libro;
import it.uniroma3.siw.siwbooks.service.AutoreService;
import it.uniroma3.siw.siwbooks.service.LibroService;
import it.uniroma3.siw.siwbooks.service.RecensioneService; // Added import

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutoreService autoreService;

    @Autowired // Added RecensioneService
    private RecensioneService recensioneService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        // Logica per la dashboard admin
        return "admin/dashboard"; // Vista Thymeleaf per la dashboard
    }

    // === Gestione Libri ===
    @GetMapping("/libri/new")
    public String newLibroForm(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("tuttiAutori", autoreService.findAll()); // cambiato da "autori" a "tuttiAutori" per coerenza col form
        return "admin/formLibro";
    }

    @PostMapping("/libri")
    public String saveLibro(@ModelAttribute Libro libro, @RequestParam(name = "autoriIds", required = false) List<Long> autoriIds, Model model) { // Modificato da Long autoreId a List<Long> autoriIds
        if (autoriIds != null && !autoriIds.isEmpty()) {
            List<Autore> autoriSelezionati = new ArrayList<>();
            for (Long autoreId : autoriIds) {
                Autore autore = autoreService.findById(autoreId);
                if (autore != null) {
                    autoriSelezionati.add(autore);
                }
            }
            libro.setAutori(autoriSelezionati); // Modificato da setAutore a setAutori
        }
        // Logica per salvare il libro, gestione immagini
        libroService.save(libro);
        return "redirect:/libri/" + libro.getId();
    }

    @GetMapping("/libri/{id}/edit")
    public String editLibroForm(@PathVariable("id") Long id, Model model) {
        Libro libro = libroService.findById(id);
        if (libro != null) {
            model.addAttribute("libro", libro);
            model.addAttribute("tuttiAutori", autoreService.findAll()); // cambiato da "autori" a "tuttiAutori"
            return "admin/formLibro";
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/libri/{id}/delete")
    public String deleteLibro(@PathVariable("id") Long id, Model model) {
        // Recupera il libro prima di eliminarlo
        Libro libro = libroService.findById(id);
        if (libro != null) {
            // Rimuovi il libro dagli autori associati (relazione bidirezionale)
            if (libro.getAutori() != null) {
                // Crea una copia della lista per evitare ConcurrentModificationException
                List<Autore> autoriAssociati = new ArrayList<>(libro.getAutori());
                for (Autore autore : autoriAssociati) {
                    if (autore.getLibri() != null) {
                        autore.getLibri().remove(libro);
                        autoreService.save(autore); // Salva l'autore aggiornato
                    }
                }
            }
            // Ora elimina il libro
            libroService.deleteById(id);
        }
        return "redirect:/admin/manageLibri";
    }

    // Nuovo metodo per gestire la pagina di gestione dei libri
    @GetMapping("/manageLibri")
    public String manageLibri(Model model) {
        model.addAttribute("libri", libroService.findAll());
        return "admin/manageLibri"; // Nome del nuovo template HTML
    }

    // === Gestione Autori ===
    @GetMapping("/autori/new")
    public String newAutoreForm(Model model) {
        model.addAttribute("autore", new Autore());
        return "admin/formAutore";
    }

    @PostMapping("/autori")
    public String saveAutore(@ModelAttribute Autore autore, Model model) {
        // Logica per salvare l'autore, gestione foto
        autoreService.save(autore);
        // Redirect alla pagina di gestione autori dopo il salvataggio
        return "redirect:/admin/manageAutori"; 
    }

    @GetMapping("/autori/{id}/edit")
    public String editAutoreForm(@PathVariable("id") Long id, Model model) {
        Autore autore = autoreService.findById(id);
        if (autore != null) {
            model.addAttribute("autore", autore);
            return "admin/formAutore";
        }
        // Redirect alla pagina di gestione autori se l'autore non viene trovato
        return "redirect:/admin/manageAutori";
    }

    @GetMapping("/autori/{id}/delete")
    public String deleteAutore(@PathVariable("id") Long id, Model model) {
        // Recupera l'autore prima di eliminarlo
        Autore autore = autoreService.findById(id);
        if (autore != null) {
            // Rimuovi l'autore da tutti i libri associati
            if (autore.getLibri() != null) {
                // Crea una copia della lista per evitare ConcurrentModificationException
                List<Libro> libriAssociati = new ArrayList<>(autore.getLibri());
                for (Libro libro : libriAssociati) {
                    libro.getAutori().remove(autore);
                    libroService.save(libro); // Salva il libro aggiornato
                }
            }
            // Ora elimina l'autore
            autoreService.deleteById(id);
        }
        // Redirect alla pagina di gestione autori dopo la cancellazione
        return "redirect:/admin/manageAutori";
    }

    @GetMapping("/manageAutori")
    public String manageAutori(Model model) {
        model.addAttribute("autori", autoreService.findAll());
        return "admin/manageAutori";
    }

    // === Gestione Recensioni ===
    @GetMapping("/manageRecensioni")
    public String manageRecensioni(Model model) {
        model.addAttribute("recensioni", recensioneService.findAll());
        return "admin/manageRecensioni";
    }

    // La cancellazione delle recensioni è in RecensioneController ma accessibile solo ad admin
}