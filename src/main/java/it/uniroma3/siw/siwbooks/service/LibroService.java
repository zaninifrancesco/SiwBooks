package it.uniroma3.siw.siwbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.siwbooks.model.Libro;
import it.uniroma3.siw.siwbooks.repository.LibroRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // Metodi per la logica di business relativa ai libri
    // Esempio: Trova tutti i libri
    public Iterable<Libro> findAll() {
        return libroRepository.findAll();
    }

    // Esempio: Trova un libro per ID
    public Libro findById(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    // Esempio: Salva un libro
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    // Esempio: Cancella un libro per ID
    public void deleteById(Long id) {
        libroRepository.deleteById(id);
    }
}
