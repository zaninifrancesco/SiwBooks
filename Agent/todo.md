# 📚 SIWBooks - ToDo List

Sistema informativo per la gestione di libri, autori e recensioni con Spring Boot e PostgreSQL.

---

## 🧠 1. Analisi e Progettazione

- [ ] Definire requisiti funzionali e non funzionali
- [ ] Creare diagramma dei casi d'uso (almeno 6)
- [ ] Creare diagramma UML delle classi e relazioni
- [ ] Progettare struttura tabelle e relazioni per PostgreSQL

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

- [ ] Scrive una recensione (una sola per libro) <!-- Manca logica per "una sola per libro" -->
- [ ] Visualizza recensioni proprie <!-- Manca endpoint/pagina dedicata -->
- [ ] Validazione voto (1–5) <!-- Manca validazione su entità/service -->

### 👨‍🏫 Amministratore

- [ ] Aggiunge nuovo libro (con autori e immagini) <!-- Dipende da gestione immagini completa -->
- [x] Aggiunge nuovo autore
- [x] Modifica autore
- [x] Cancella recensione <!-- Verifica permessi Admin in SecurityConfig/Controller -->
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
  - [ ] Scrivi recensione <!-- Manca template formRecensione.html -->
  - [x] Dashboard Admin (inserimento, modifica, cancellazione)

---

## 💾 8. Database PostgreSQL

- [x] Creazione schema (automatizzato da JPA)
- [ ] Popolamento iniziale (opzionale: `data.sql`)
- [ ] Test con dati reali e immagini caricate <!-- Dipende da gestione immagini -->

---

## 🧪 9. Testing (opzionale)

- [ ] Test unitari per service
- [ ] Test di integrazione
- [ ] Validazione input utente (lato server e lato client)

---

## 📦 10. Consegna finale

- [ ] README.md completo (con build, run, credenziali test, struttura progetto)
- [ ] Codice ben commentato e formattato
- [ ] Demo funzionante (se richiesta)