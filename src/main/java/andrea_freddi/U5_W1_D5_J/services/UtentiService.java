package andrea_freddi.U5_W1_D5_J.services;

import andrea_freddi.U5_W1_D5_J.entities.Utente;
import andrea_freddi.U5_W1_D5_J.exceptions.ValidationException;
import andrea_freddi.U5_W1_D5_J.repositories.UtentiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UtentiService {
    // richiamo il repository degli utenti
    @Autowired
    private UtentiRepository utentiRepository;

    // creo un metodo per trovare tutti gli utenti
    public List<Utente> recuperaUtenti() {
        return utentiRepository.findAll();
    }

    // creo un metodo per salvare un nuovo utente
    public void salvaUtente(Utente nuovoUtente) {
        // prima faccio un controllo su username e email per essere certo che l'utente non esista già
        if (utentiRepository.existsByUsername(nuovoUtente.getUsername()))
            throw new ValidationException("Username già esistente");
        else if (utentiRepository.existsByEmail(nuovoUtente.getEmail()))
            throw new ValidationException("Email già esistente");
        // se l'utente non esiste già lo salvo nel database
        utentiRepository.save(nuovoUtente);
        // loggo l'avvenuto salvataggio
        log.info("L'utente " + nuovoUtente.getNome() + " " + nuovoUtente.getCognome() + " è stato salvato correttamente nel database");
    }

    // creo un metodo per salvare più utenti
    public void salvaMoltiUtenti(List<Utente> nuoviUtenti) {
        for (Utente utente : nuoviUtenti) {
            try {
                this.salvaUtente(utente);
            } catch (ValidationException ex) {
                log.error(ex.getMessage());
            }
        }
    }
}
