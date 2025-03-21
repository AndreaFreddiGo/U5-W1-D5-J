package andrea_freddi.U5_W1_D5_J.services;

import andrea_freddi.U5_W1_D5_J.entities.Prenotazione;
import andrea_freddi.U5_W1_D5_J.exceptions.ValidationException;
import andrea_freddi.U5_W1_D5_J.repositories.PrenotazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PrenotazioniService {
    // richiamo il repository delle prenotazioni
    @Autowired
    private PrenotazioniRepository prenotazioniRepository;

    // creo un metodo per trovare tutte le prenotazioni
    public List<Prenotazione> recuperaPrenotazioni() {
        return prenotazioniRepository.findAll();
    }

    // creo un metodo per salvare una nuova prenotazione
    public void salvaPrenotazione(Prenotazione nuovaPrenotazione) {

        if (prenotazioniRepository.existsByPostazioneAndData(nuovaPrenotazione.getPostazione(), nuovaPrenotazione.getData())) {
            // se la postazione è già prenotata per la data richiesta, lancio un'eccezione
            throw new ValidationException("La postazione " + nuovaPrenotazione.getPostazione().getDescrizione() +
                    " presso l'edificio " + nuovaPrenotazione.getPostazione().getEdificio().getNome() +
                    " è già prenotata per la data " + nuovaPrenotazione.getData());
        } else if (prenotazioniRepository.existsByUtenteAndData(nuovaPrenotazione.getUtente(), nuovaPrenotazione.getData())) {
            // se l'utente ha già prenotato una postazione per la data richiesta, lancio un'eccezione
            throw new ValidationException("L'utente " + nuovaPrenotazione.getUtente().getNome() +
                    " " + nuovaPrenotazione.getUtente().getCognome() +
                    " ha già prenotato una postazione per la data " + nuovaPrenotazione.getData());
        } else {
            prenotazioniRepository.save(nuovaPrenotazione);
            // loggo l'avvenuto salvataggio
            log.info("Prenotazione della postazione " + nuovaPrenotazione.getPostazione().getDescrizione() +
                    " presso l'edificio " + nuovaPrenotazione.getPostazione().getEdificio().getNome() +
                    " per l'utente " + nuovaPrenotazione.getUtente().getNome() + " " + nuovaPrenotazione.getUtente().getCognome() +
                    " in data " + nuovaPrenotazione.getData() + " salvata con successo nel database");
        }

    }

    // creo un metodo per salvare più prenotazioni
    public void salvaMoltePrenotazioni(List<Prenotazione> nuovePrenotazioni) {
        for (Prenotazione prenotazione : nuovePrenotazioni) {
            try {
                this.salvaPrenotazione(prenotazione);
            } catch (ValidationException ex) {
                log.error(ex.getMessage());
            }
        }

    }
}
