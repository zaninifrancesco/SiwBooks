# 📚 SIWBooks - ToDo List

Sistema informativo per la gestione di libri, autori e recensioni con Spring Boot e PostgreSQL.

---

## 🧠 1. Analisi e Progettazione

- [ ] Definire requisiti funzionali e non funzionali
- [ ] Creare diagramma dei casi d'uso (almeno 6)
- [ ] Creare diagramma UML delle classi e relazioni
- [x] Progettare struttura tabelle e relazioni per PostgreSQL

---

## 🗂️ 2. Modellazione JPA

### 📁 Entity

- [x] `Autore`: nome, cognome, dataNascita, dataMorte, nazionalità, foto
- [x] `Libro`: titolo, anno, immagini (file), autori (ManyToMany)
- [x] `Recensione`: titolo, voto, testo, utente (ManyToOne), libro (ManyToOne)
- [x] `Utente`: username, password, ruolo
- [x] `Ruolo`: enum (ADMIN, USER)

### 🔁 Relazioni

- [x] `Libro` ↔ `Autore` → ManyToMany
- [x] `Libro` ↔ `Recensione` → OneToMany
- [x] `Recensione` ↔ `Utente` → ManyToOne

---

## 🏗️ 3. Backend - Spring Boot

### 🔧 Configurazione

- [x] Configurare PostgreSQL in `application.properties`
- [x] Dipendenze Maven: Spring Boot, JPA, Security, Thymeleaf, Web, DevTools, PostgreSQL

### 📚 Repository

- [x] `LibroRepository`
- [x] `AutoreRepository`
- [x] `RecensioneRepository`
- [x] `UtenteRepository`

### ⚙️ Services

- [x] `LibroService`
- [x] `AutoreService`
- [x] `RecensioneService`
- [x] `UtenteService` (registrazione/login)

### 🌐 Controller

- [x] `LibroController`
- [x] `AutoreController`
- [x] `RecensioneController`
- [x] `AdminController`
- [x] `AuthenticationController`

---

## 🔒 4. Sicurezza (Spring Security)

- [x] Login e logout
- [x] Registrazione nuovi utenti
- [x] Configurazione ruoli e accessi (occasionale, registrato, admin)
- [x] Restrizioni URL (es. `/admin/**` accessibile solo agli admin)

---

## 🖼️ 5. Gestione Immagini

- [ ] Upload immagini libro (multi immagine) <!-- Manca logica di upload effettiva -->
- [ ] Upload foto autore (una) <!-- Manca logica di upload effettiva -->
- [ ] Salvataggio immagini nel filesystem <!-- Conseguenza di mancanza upload -->
- [ ] Salvataggio percorso immagini nel DB <!-- Campi presenti ma non popolati da upload -->
- [ ] Visualizzazione immagini nelle pagine <!-- Parzialmente tentata per autore, ma dipende da upload -->

---

## 🧑‍💼 6. Funzionalità e Casi d'Uso

### 👤 Utente Occasionale

- [x] Visualizza elenco libri
- [x] Visualizza dettaglio libro (con recensioni)
- [x] Visualizza dettaglio autore

### 🧑‍💻 Utente Registrato

- [x] Scrive una recensione (una sola per libro)
- [x] Visualizza recensioni proprie
- [x] Validazione voto (1–5)

### 👨‍🏫 Amministratore

- [x] Aggiunge nuovo libro (con autori e immagini) <!-- Funzionalità base implementata ma senza gestione immagini completa -->
- [x] Aggiunge nuovo autore
- [x] Modifica autore
- [x] Cancella recensione
- [x] Elimina libro/autore

---

## 🎨 7. Frontend - HTML + CSS

- [x] Layout base con Thymeleaf
- [x] Integrazione Bootstrap
- [x] Navigazione chiara (navbar)
- [ ] Pagine:
  - [x] Home / Lista libri
  - [x] Dettaglio libro
  - [x] Dettaglio autore
  - [x] Login / Registrazione
  - [x] Scrivi recensione <!-- Integrato in dettaglio libro -->
  - [x] Dashboard Admin (inserimento, modifica, cancellazione)

---

## 💾 8. Database PostgreSQL

- [x] Creazione schema (automatizzato da JPA)
- [x] Popolamento iniziale (opzionale: `data.sql`)
- [ ] Test con dati reali e immagini caricate <!-- Dipende da gestione immagini -->

---

## 🧪 9. Testing (opzionale)

- [ ] Test unitari per service
- [ ] Test di integrazione
- [ ] Validazione input utente (lato server e lato client)

---

## 🎯 11. Sistema Recensioni - Implementazione Dettagliata

### 📊 Database e Repository

- [x] Aggiungere campo `dataCreazione` a `Recensione` per tracciare quando è stata scritta
- [x] Aggiungere metodo `findByUtenteAndLibro(Utente utente, Libro libro)` a `RecensioneRepository`
- [x] Aggiungere metodo `findByUtente(Utente utente)` a `RecensioneRepository` per le recensioni dell'utente
- [x] Aggiungere metodo `findByLibroOrderByDataCreazioneDesc(Libro libro)` per ordinare recensioni

### 🔧 Service

- [x] Aggiungere metodo `existsRecensioneByUtenteAndLibro(Utente utente, Libro libro)` al `RecensioneService`
- [x] Implementare validazione del voto (1-5) con `@Min(1)` e `@Max(5)` su campo `voto`
- [x] Aggiungere logica per impostare automaticamente `dataCreazione` quando si salva una recensione
- [x] Implementare metodo `getRecensioniUtente(Utente utente)` per dashboard utente

### 🎮 Controller

- [x] Completare metodo `newRecensioneForm` per verificare che l'utente non abbia già recensito il libro <!-- Ora integrato in libro.html -->
- [x] Reindirizzare con messaggio di errore se l'utente ha già recensito il libro <!-- Gestito in libro.html e LibroController -->
- [x] Ampliare metodo `saveRecensione` per:
  - [x] Associare l'utente corrente (recuperandolo da `SecurityContextHolder`)
  - [x] Associare il libro corretto
  - [x] Validare il voto (1-5)
  - [x] Impostare `dataCreazione`
- [x] Creare endpoint `GET /utente/recensioni` per la dashboard utente
- [x] Aggiungere messaggio di conferma dopo il salvataggio di una recensione

### 🎨 Frontend

- [x] Creare template `formRecensione.html` con: <!-- Ora integrato in libro.html -->
  - [x] Campo titolo recensione
  - [x] Campo voto (1-5) con validazione client-side
  - [x] Area di testo per il contenuto
  - [x] Submit button
  - [x] Messaggi di validazione
- [x] Aggiungere in `libro.html` un bottone "Scrivi recensione" per utenti autenticati <!-- Ora è un form inline -->
- [x] Nascondere il bottone "Scrivi recensione" se l'utente ha già recensito il libro <!-- Form inline nascosto -->
- [x] Creare template `recensioniUtente.html` per visualizzare le recensioni proprie
- [x] Aggiungere componente di visualizzazione stelle per i voti (1-5)
- [x] Aggiungere link alla dashboard recensioni nella navbar per utenti autenticati

### 🔐 Sicurezza

- [x] Verificare che `/libri/{id}/recensioni/new` sia accessibile solo agli utenti autenticati <!-- Ora reindirizza -->
- [x] Verificare che `/libri/{id}/recensioni` per il POST sia accessibile agli utenti autenticati
- [x] Assicurarsi che `/recensioni/{id}/delete` sia accessibile solo agli admin
- [x] Proteggere l'endpoint `/utente/recensioni` per utenti autenticati

---

## 📦 10. Consegna finale

- [ ] README.md completo (con build, run, credenziali test, struttura progetto)
- [ ] Codice ben commentato e formattato
- [ ] Demo funzionante (se richiesta)