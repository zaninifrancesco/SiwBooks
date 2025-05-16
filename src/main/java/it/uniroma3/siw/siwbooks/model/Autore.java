package it.uniroma3.siw.siwbooks.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private LocalDate dataMorte;
    private String nazionalita;
    private String foto; // Percorso del file dell'immagine

    @ManyToMany(mappedBy = "autori")
    private List<Libro> libri;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public LocalDate getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(LocalDate dataMorte) {
        this.dataMorte = dataMorte;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public void setLibri(List<Libro> libri) {
        this.libri = libri;
    }
}
