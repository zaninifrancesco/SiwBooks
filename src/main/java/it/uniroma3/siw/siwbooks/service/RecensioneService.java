package it.uniroma3.siw.siwbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.siwbooks.model.Recensione;
import it.uniroma3.siw.siwbooks.repository.RecensioneRepository;

@Service
public class RecensioneService {

    @Autowired
    private RecensioneRepository recensioneRepository;

    public Iterable<Recensione> findAll() {
        return recensioneRepository.findAll();
    }

    public Recensione findById(Long id) {
        return recensioneRepository.findById(id).orElse(null);
    }

    public Recensione save(Recensione recensione) {
        return recensioneRepository.save(recensione);
    }

    public void deleteById(Long id) {
        recensioneRepository.deleteById(id);
    }
}
