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

- [ ] Upload immagini libro (multi immagine)
- [ ] Upload foto autore (una)
- [ ] Salvataggio immagini nel filesystem
- [ ] Salvataggio percorso immagini nel DB
- [ ] Visualizzazione immagini nelle pagine

---

## 🧑‍💼 6. Funzionalità e Casi d'Uso

### 👤 Utente Occasionale

- [x] Visualizza elenco libri
- [x] Visualizza dettaglio libro (con recensioni)
- [x] Visualizza dettaglio autore

### 🧑‍💻 Utente Registrato

- [x] Scrive una recensione (una sola per libro) <!-- Base functionality exists, "una sola" logic might need refinement -->
- [ ] Visualizza recensioni proprie
- [ ] Validazione voto (1–5)

### 👨‍🏫 Amministratore

- [x] Aggiunge nuovo libro (con autori e immagini) <!-- Verificare se gestione immagini è completa -->
- [x] Aggiunge nuovo autore
- [x] Modifica autore
- [x] Cancella recensione <!-- Verifica permessi Admin in SecurityConfig/Controller -->
- [x] Elimina libro/autore

---

## 🎨 7. Frontend - HTML + CSS

- [ ] Layout base con Thymeleaf
- [ ] Integrazione Bootstrap
- [ ] Navigazione chiara (navbar)
- [ ] Pagine:
  - [x] Home / Lista libri
  - [x] Dettaglio libro
  - [x] Dettaglio autore
  - [x] Login / Registrazione
  - [ ] Scrivi recensione
  - [x] Dashboard Admin (inserimento, modifica, cancellazione) <!-- formLibro.html e formRecensione.html mancanti o non listati -->

---

## 💾 8. Database PostgreSQL

- [ ] Creazione schema (automatizzato da JPA)
- [ ] Popolamento iniziale (opzionale: `data.sql`)
- [ ] Test con dati reali e immagini caricate

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