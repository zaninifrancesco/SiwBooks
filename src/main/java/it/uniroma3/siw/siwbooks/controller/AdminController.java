package it.uniroma3.siw.siwbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import it.uniroma3.siw.siwbooks.model.Libro;
import it.uniroma3.siw.siwbooks.model.Autore;
import it.uniroma3.siw.siwbooks.service.LibroService;
import it.uniroma3.siw.siwbooks.service.AutoreService;

@Controller
@RequestMapping("/admin") // Aggiungiamo un prefisso per tutte le rotte admin
public class AdminController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutoreService autoreService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        // Logica per la dashboard admin
        return "admin/dashboard"; // Vista Thymeleaf per la dashboard
    }

    // === Gestione Libri ===
    @GetMapping("/libri/new")
    public String newLibroForm(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("autori", autoreService.findAll()); // Per selezionare autori esistenti
        return "admin/formLibro";
    }

    @PostMapping("/libri")
    public String saveLibro(@ModelAttribute Libro libro, Model model) {
        // Logica per salvare il libro, gestione immagini e autori
        libroService.save(libro);
        return "redirect:/libri/" + libro.getId();
    }

    @GetMapping("/libri/{id}/edit")
    public String editLibroForm(@PathVariable("id") Long id, Model model) {
        Libro libro = libroService.findById(id);
        if (libro != null) {
            model.addAttribute("libro", libro);
            model.addAttribute("autori", autoreService.findAll());
            return "admin/formLibro";
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/libri/{id}/delete")
    public String deleteLibro(@PathVariable("id") Long id, Model model) {
        libroService.deleteById(id);
        return "redirect:/libri";
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
        autoreService.deleteById(id);
        // Redirect alla pagina di gestione autori dopo la cancellazione
        return "redirect:/admin/manageAutori";
    }

    @GetMapping("/manageAutori")
    public String manageAutori(Model model) {
        model.addAttribute("autori", autoreService.findAll());
        return "admin/manageAutori";
    }

    // La cancellazione delle recensioni è in RecensioneController ma accessibile solo ad admin
}