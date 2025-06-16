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
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.siwbooks.model.Autore;
import it.uniroma3.siw.siwbooks.model.Libro;
import it.uniroma3.siw.siwbooks.service.AutoreService;
import it.uniroma3.siw.siwbooks.service.LibroService;
import it.uniroma3.siw.siwbooks.service.RecensioneService;
import it.uniroma3.siw.siwbooks.service.ImageService;

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
    
    @Autowired
    private ImageService imageService;

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
    public String saveLibro(@ModelAttribute Libro libro, 
                           @RequestParam(name = "autoriIds", required = false) List<Long> autoriIds,
                           @RequestParam(name = "immaginiFile", required = false) MultipartFile[] immaginiFile,
                           Model model) {
        
        try {
            // Gestione autori
            if (autoriIds != null && !autoriIds.isEmpty()) {
                List<Autore> autoriSelezionati = new ArrayList<>();
                for (Long autoreId : autoriIds) {
                    Autore autore = autoreService.findById(autoreId);
                    if (autore != null) {
                        autoriSelezionati.add(autore);
                    }
                }
                libro.setAutori(autoriSelezionati);
            }
            
            // Gestione upload immagini
            if (immaginiFile != null && immaginiFile.length > 0) {
                // Verifica se ci sono nuove immagini da caricare
                boolean hasNewImages = false;
                for (MultipartFile file : immaginiFile) {
                    if (!file.isEmpty()) {
                        hasNewImages = true;
                        break;
                    }
                }
                
                if (hasNewImages) {
                    // Se stiamo modificando un libro esistente e ci sono nuove immagini,
                    // elimina le vecchie immagini prima di aggiungere le nuove
                    if (libro.getId() != null) {
                        Libro libroEsistente = libroService.findById(libro.getId());
                        if (libroEsistente != null && libroEsistente.getImmagini() != null && !libroEsistente.getImmagini().isEmpty()) {
                            imageService.deleteImages(libroEsistente.getImmagini());
                            System.out.println("Eliminate vecchie immagini per il libro: " + libro.getTitolo());
                        }
                    }
                    
                    // Carica le nuove immagini
                    List<String> imagePaths = imageService.saveImages(immaginiFile);
                    if (!imagePaths.isEmpty()) {
                        libro.setImmagini(imagePaths);
                        System.out.println("Caricate " + imagePaths.size() + " nuove immagini per il libro: " + libro.getTitolo());
                    }
                }
            }
            
            libroService.save(libro);
            return "redirect:/libri/" + libro.getId();
            
        } catch (Exception e) {
            model.addAttribute("errore", "Errore durante il salvataggio: " + e.getMessage());
            model.addAttribute("libro", libro);
            model.addAttribute("tuttiAutori", autoreService.findAll());
            return "admin/formLibro";
        }
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
            // Elimina le immagini associate
            if (libro.getImmagini() != null && !libro.getImmagini().isEmpty()) {
                imageService.deleteImages(libro.getImmagini());
                System.out.println("Eliminate " + libro.getImmagini().size() + " immagini per il libro: " + libro.getTitolo());
            }
            
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
            System.out.println("Libro eliminato: " + libro.getTitolo());
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
    public String saveAutore(@ModelAttribute Autore autore,
                            @RequestParam(name = "fotoFile", required = false) MultipartFile fotoFile,
                            Model model) {
        
        try {
            // Gestione upload foto autore
            if (fotoFile != null && !fotoFile.isEmpty()) {
                // Se stiamo modificando un autore esistente e c'è una nuova foto,
                // elimina la vecchia foto prima di aggiungere la nuova
                if (autore.getId() != null) {
                    Autore autoreEsistente = autoreService.findById(autore.getId());
                    if (autoreEsistente != null && autoreEsistente.getFoto() != null) {
                        imageService.deleteImage(autoreEsistente.getFoto());
                        System.out.println("Eliminata vecchia foto per l'autore: " + autore.getNome() + " " + autore.getCognome());
                    }
                }
                
                // Carica la nuova foto
                String fotoPath = imageService.saveImage(fotoFile);
                autore.setFoto(fotoPath);
                System.out.println("Caricata nuova foto per l'autore: " + autore.getNome() + " " + autore.getCognome());
            }
            
            autoreService.save(autore);
            return "redirect:/admin/manageAutori";
            
        } catch (Exception e) {
            model.addAttribute("errore", "Errore durante il salvataggio: " + e.getMessage());
            model.addAttribute("autore", autore);
            return "admin/formAutore";
        }
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
            // Elimina la foto dell'autore
            if (autore.getFoto() != null) {
                imageService.deleteImage(autore.getFoto());
                System.out.println("Eliminata foto per l'autore: " + autore.getNome() + " " + autore.getCognome());
            }
            
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
            System.out.println("Autore eliminato: " + autore.getNome() + " " + autore.getCognome());
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