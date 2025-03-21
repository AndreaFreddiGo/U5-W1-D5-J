package andrea_freddi.U5_W1_D5_J.services;

import andrea_freddi.U5_W1_D5_J.entities.Postazione;
import andrea_freddi.U5_W1_D5_J.entities.Tipo;
import andrea_freddi.U5_W1_D5_J.exceptions.ValidationException;
import andrea_freddi.U5_W1_D5_J.repositories.PostazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostazioniService {
    // richiamo il repository delle postazioni
    @Autowired
    private PostazioniRepository postazioniRepository;

    // creo un metodo per trovare tutte le postazioni
    public List<Postazione> recuperaPostazioni() {
        return postazioniRepository.findAll();
    }

    // creo un metodo per salvare una nuova postazione
    public void salvaPostazione(Postazione nuovaPostazione) {
        postazioniRepository.save(nuovaPostazione);
        // loggo l'avvenuto salvataggio
        log.info("La postazione " + nuovaPostazione.getDescrizione() + ", di tipo " + nuovaPostazione.getTipo() + " è stat salvata correttamente nel database");
    }

    // creo un metodo per salvare più postazioni
    public void salvaMoltePostazioni(List<Postazione> nuovePostazioni) {
        for (Postazione postazione : nuovePostazioni) {
            try {
                salvaPostazione(postazione);
            } catch (ValidationException ex) {
                log.error(ex.getMessage());
            }
        }
    }

    // creo un metodo per trovare una postazione per tipo e per città
    public List<Postazione> trovaPostazioniPerTipoECittà(Tipo tipo, String città) {
        return postazioniRepository.findByTipoAndEdificio_Città(tipo, città);
    }

}
