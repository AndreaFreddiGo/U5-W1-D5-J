package andrea_freddi.U5_W1_D5_J.services;

import andrea_freddi.U5_W1_D5_J.entities.Edificio;
import andrea_freddi.U5_W1_D5_J.exceptions.ValidationException;
import andrea_freddi.U5_W1_D5_J.repositories.EdificiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EdificiService {
    // richiamo il repository degli edifici
    @Autowired
    private EdificiRepository edificiRepository;

    // creo un metodo per trovare tutti gli edifici
    public List<Edificio> recuperaEdifici() {
        return edificiRepository.findAll();
    }

    // creo un metodo per salvare un nuovo edificio
    public void salvaEdificio(Edificio edificio) {
        // prima faccio un controllo sul nome per essere certo che l'edificio non esista già
        if (edificiRepository.existsByNome(edificio.getNome())) throw new ValidationException("Edificio già esistente");
        // se l'edificio non esiste già, lo salvo ne database
        edificiRepository.save(edificio);
        // loggo l'avvenuto salvataggio
        log.info("L'edificio " + edificio.getNome() + " è stato salvato correttamente nel database!");
    }

    // creo un metodo per salvare più edifici
    public void salvaMoltiEdifici(List<Edificio> nuoviEdifici) {
        for (Edificio edificio : nuoviEdifici) {
            try {
                this.salvaEdificio(edificio);
            } catch (ValidationException ex) {
                log.error(ex.getMessage());
            }
        }
    }
}
