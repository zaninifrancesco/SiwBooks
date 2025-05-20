package it.uniroma3.siw.siwbooks.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany; // Aggiunto import per @ManyToMany
import jakarta.persistence.OneToMany;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titolo;
    private Integer anno;

    @ElementCollection // Per una lista di stringhe semplici (percorsi dei file immagine)
    private List<String> immagini;

    @ManyToMany
    private List<Autore> autori; // Ripristinato a List<Autore> con @ManyToMany

    @OneToMany(mappedBy = "libro")
    private List<Recensione> recensioni;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public List<String> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<String> immagini) {
        this.immagini = immagini;
    }

    public List<Autore> getAutori() { // Getter aggiornato
        return autori;
    }

    public void setAutori(List<Autore> autori) { // Setter aggiornato
        this.autori = autori;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }
}
