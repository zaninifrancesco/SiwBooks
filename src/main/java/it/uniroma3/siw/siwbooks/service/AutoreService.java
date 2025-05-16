package it.uniroma3.siw.siwbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.siwbooks.model.Autore;
import it.uniroma3.siw.siwbooks.repository.AutoreRepository;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    public Iterable<Autore> findAll() {
        return autoreRepository.findAll();
    }

    public Autore findById(Long id) {
        return autoreRepository.findById(id).orElse(null);
    }

    public Autore save(Autore autore) {
        return autoreRepository.save(autore);
    }

    public void deleteById(Long id) {
        autoreRepository.deleteById(id);
    }
}
