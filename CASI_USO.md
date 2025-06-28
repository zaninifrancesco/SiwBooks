# Casi d'Uso - SiwBooks

## Descrizione del Sistema
SiwBooks è una piattaforma web per la gestione di una libreria digitale che permette agli utenti di visualizzare libri, autori e scrivere recensioni. Gli amministratori possono gestire il catalogo librario.

---

## 1. Visualizzazione Homepage
**Attore:** Visitatore (Utente non autenticato/autenticato)  
**Descrizione:** L'utente visualizza la homepage del sistema che mostra una selezione di libri e autori in evidenza.  
**Precondizioni:** Nessuna  
**Flusso principale:**
1. L'utente accede alla homepage "/"
2. Il sistema mostra i primi 6 libri disponibili
3. Il sistema mostra i primi 6 autori disponibili
4. L'utente può navigare verso le sezioni complete di libri o autori

**Postcondizioni:** L'utente ha una panoramica del contenuto disponibile

---

## 2. Registrazione Nuovo Utente
**Attore:** Visitatore  
**Descrizione:** Un visitatore si registra al sistema per diventare un utente registrato.  
**Precondizioni:** L'utente non è autenticato  
**Flusso principale:**
1. L'utente accede al form di registrazione "/register"
2. L'utente inserisce username e password
3. Il sistema valida i dati inseriti
4. Il sistema cripta la password
5. Il sistema salva l'utente con ruolo USER
6. L'utente viene reindirizzato alla homepage

**Flusso alternativo:**
- Se l'username esiste già, il sistema mostra un errore

**Postcondizioni:** L'utente è registrato e può effettuare il login

---

## 3. Autenticazione Utente
**Attore:** Utente registrato  
**Descrizione:** Un utente registrato effettua il login al sistema.  
**Precondizioni:** L'utente è registrato ma non autenticato  
**Flusso principale:**
1. L'utente accede al form di login "/login"
2. L'utente inserisce username e password
3. Il sistema verifica le credenziali
4. Il sistema autentica l'utente e crea una sessione
5. L'utente viene reindirizzato alla homepage

**Flusso alternativo:**
- Se le credenziali sono errate, il sistema mostra un errore

**Postcondizioni:** L'utente è autenticato e può accedere alle funzionalità riservate

---

## 4. Visualizzazione Catalogo Libri
**Attore:** Visitatore/Utente autenticato  
**Descrizione:** L'utente esplora il catalogo completo dei libri disponibili.  
**Precondizioni:** Nessuna  
**Flusso principale:**
1. L'utente accede alla sezione libri "/libri"
2. Il sistema mostra tutti i libri con titolo, anno e autori
3. L'utente può cliccare su un libro per vedere i dettagli

**Postcondizioni:** L'utente ha visualizzato l'elenco dei libri

---

## 5. Visualizzazione Dettagli Libro e Recensioni
**Attore:** Visitatore/Utente autenticato  
**Descrizione:** L'utente visualizza i dettagli di un libro specifico e le recensioni associate.  
**Precondizioni:** Il libro deve esistere nel sistema  
**Flusso principale:**
1. L'utente accede ai dettagli del libro "/libri/{id}"
2. Il sistema mostra:
   - Dettagli del libro (titolo, anno, immagini)
   - Lista degli autori
   - Tutte le recensioni ordinate per data (più recenti prima)
3. Se l'utente è autenticato e non ha già recensito, mostra il form per aggiungere una recensione

**Postcondizioni:** L'utente ha visualizzato tutti i dettagli del libro e le recensioni

---

## 6. Scrittura Recensione Libro
**Attore:** Utente autenticato  
**Descrizione:** Un utente autenticato scrive una recensione per un libro.  
**Precondizioni:** 
- L'utente deve essere autenticato
- L'utente non deve aver già recensito il libro
- Il libro deve esistere  

**Flusso principale:**
1. L'utente visualizza la pagina del libro
2. L'utente compila il form di recensione con:
   - Titolo della recensione (obbligatorio, max 100 caratteri)
   - Voto da 1 a 5 stelle (obbligatorio)
   - Testo della recensione (opzionale, max 1000 caratteri)
3. L'utente invia la recensione
4. Il sistema valida i dati
5. Il sistema salva la recensione associandola all'utente e al libro
6. Il sistema imposta automaticamente la data di creazione
7. L'utente viene reindirizzato alla pagina del libro con messaggio di successo

**Flusso alternativo:**
- Se l'utente ha già recensito il libro, il sistema mostra un errore
- Se ci sono errori di validazione, il sistema mostra i messaggi di errore

**Postcondizioni:** La recensione è stata salvata e visibile nella pagina del libro

---

## 7. Visualizzazione Proprie Recensioni
**Attore:** Utente autenticato  
**Descrizione:** Un utente visualizza tutte le recensioni che ha scritto.  
**Precondizioni:** L'utente deve essere autenticato  
**Flusso principale:**
1. L'utente accede alla sezione delle proprie recensioni "/utente/recensioni"
2. Il sistema mostra tutte le recensioni scritte dall'utente con:
   - Titolo della recensione
   - Voto assegnato
   - Data di creazione
   - Link al libro recensito

**Postcondizioni:** L'utente ha visualizzato tutte le sue recensioni

---

## 8. Gestione Libri (Admin)
**Attore:** Amministratore  
**Descrizione:** Un amministratore gestisce il catalogo dei libri.  
**Precondizioni:** L'utente deve essere autenticato con ruolo ADMIN  
**Flusso principale:**
1. L'admin accede al pannello di amministrazione "/admin/manageLibri"
2. L'admin può:
   - Visualizzare tutti i libri esistenti
   - Creare un nuovo libro ("/admin/libri/new")
   - Modificare un libro esistente ("/admin/libri/{id}/edit")
   - Eliminare un libro ("/admin/libri/{id}/delete")
3. Durante la creazione/modifica può:
   - Inserire titolo e anno di pubblicazione
   - Associare uno o più autori
   - Caricare immagini del libro
4. Quando elimina un libro, vengono eliminate anche le immagini associate

**Postcondizioni:** Il catalogo libri è stato aggiornato

---

## 9. Gestione Autori (Admin)
**Attore:** Amministratore  
**Descrizione:** Un amministratore gestisce gli autori nel sistema.  
**Precondizioni:** L'utente deve essere autenticato con ruolo ADMIN  
**Flusso principale:**
1. L'admin accede al pannello di gestione autori "/admin/manageAutori"
2. L'admin può:
   - Visualizzare tutti gli autori esistenti
   - Creare un nuovo autore ("/admin/autori/new")
   - Modificare un autore esistente ("/admin/autori/{id}/edit")
   - Eliminare un autore ("/admin/autori/{id}/delete")
3. Durante la creazione/modifica può inserire:
   - Nome e cognome
   - Data di nascita e morte
   - Nazionalità
   - Foto dell'autore
4. Quando elimina un autore, viene rimosso da tutti i libri associati

**Postcondizioni:** Il catalogo autori è stato aggiornato

---

## 10. Gestione Recensioni (Admin)
**Attore:** Amministratore  
**Descrizione:** Un amministratore può eliminare recensioni inappropriate.  
**Precondizioni:** L'utente deve essere autenticato con ruolo ADMIN  
**Flusso principale:**
1. L'admin visualizza una recensione nella pagina di un libro
2. L'admin clicca sul pulsante "Elimina" presente in ogni recensione
3. Il sistema chiede conferma
4. L'admin conferma l'eliminazione
5. Il sistema elimina la recensione
6. L'admin viene reindirizzato alla pagina del libro con messaggio di successo

**Postcondizioni:** La recensione è stata eliminata dal sistema

---

## 11. Visualizzazione Catalogo Autori
**Attore:** Visitatore/Utente autenticato  
**Descrizione:** L'utente esplora il catalogo degli autori disponibili.  
**Precondizioni:** Nessuna  
**Flusso principale:**
1. L'utente accede alla sezione autori "/autori"
2. Il sistema mostra tutti gli autori con nome, cognome e informazioni base
3. L'utente può cliccare su un autore per vedere i dettagli

**Postcondizioni:** L'utente ha visualizzato l'elenco degli autori

---

## 12. Visualizzazione Dettagli Autore
**Attore:** Visitatore/Utente autenticato  
**Descrizione:** L'utente visualizza i dettagli di un autore specifico e i suoi libri.  
**Precondizioni:** L'autore deve esistere nel sistema  
**Flusso principale:**
1. L'utente accede ai dettagli dell'autore "/autori/{id}"
2. Il sistema mostra:
   - Informazioni biografiche dell'autore
   - Foto dell'autore (se disponibile)
   - Lista dei libri scritti dall'autore
3. L'utente può cliccare sui libri per vederne i dettagli

**Postcondizioni:** L'utente ha visualizzato tutti i dettagli dell'autore

---

## Attori del Sistema

### Visitatore
- Utente non autenticato
- Può visualizzare homepage, libri, autori e recensioni
- Non può scrivere recensioni

### Utente Registrato
- Utente autenticato con ruolo USER
- Tutte le funzionalità del visitatore
- Può scrivere recensioni
- Può visualizzare le proprie recensioni

### Amministratore
- Utente autenticato con ruolo ADMIN
- Tutte le funzionalità dell'utente registrato
- Può gestire il catalogo libri e autori
- Può eliminare recensioni inappropriate

## Tecnologie Utilizzate
- **Framework:** Spring Boot
- **Template Engine:** Thymeleaf
- **Sicurezza:** Spring Security
- **Persistenza:** JPA/Hibernate
- **Database:** PostgreSQL (inferito dalla configurazione)
- **Frontend:** Bootstrap + FontAwesome
