package andrea_freddi.U5_W1_D5_J.runners;

import andrea_freddi.U5_W1_D5_J.entities.Postazione;
import andrea_freddi.U5_W1_D5_J.entities.Prenotazione;
import andrea_freddi.U5_W1_D5_J.entities.Utente;
import andrea_freddi.U5_W1_D5_J.services.PostazioniService;
import andrea_freddi.U5_W1_D5_J.services.PrenotazioniService;
import andrea_freddi.U5_W1_D5_J.services.UtentiService;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
@Order(4) // definisco l'ordine di esecuzione del runner (questo è l'ultimo perché mi servono i dati degli altri)
public class PrenotazioniRunner implements CommandLineRunner {
    // creo un'istanza del service
    @Autowired
    private PrenotazioniService prenotazioniService;
    // creo anche l'istanza del service per le postazioni perché mi servirà per creare le prenotazioni
    @Autowired
    private PostazioniService postazioniService;
    // creo anche l'istanza del service per gli utenti perché mi servirà per creare le prenotazioni
    @Autowired
    private UtentiService utentiService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.ITALY);

        // recupero la lista di tutti gli utenti e tutte le postazioni che mi serviranno per creare le prenotazioni random
        List<Utente> utenti = utentiService.recuperaUtenti();
        List<Postazione> postazioni = postazioniService.recuperaPostazioni();

        // recupero un utente e una postazione casuali
        Utente utenteCasuale = utenti.get(faker.random().nextInt(utenti.size()));
        Postazione postazioneCasuale = postazioni.get(faker.random().nextInt(postazioni.size()));

        // genero una data casuale tra domani e tra 30 giorni
        LocalDate dataRandom = LocalDate.now().plusDays(ThreadLocalRandom.current().nextLong(1, 31));


        // intanto creo 30 prenotazioni da inserire nel database
        List<Prenotazione> nuovePrenotazioni = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Prenotazione nuovaPrenotazione = new Prenotazione(dataRandom, postazioneCasuale, utenteCasuale);
            nuovePrenotazioni.add(nuovaPrenotazione);
        }

        // salvo le prenotazioni solo dopo che avranno superato le verifiche di validità
//        try {
//            prenotazioniService.salvaMoltePrenotazioni(nuovePrenotazioni);
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//        }

        // recupero tutte le prenotazioni e ne scelgo una a caso
        List<Prenotazione> prenotazioni = prenotazioniService.recuperaPrenotazioni();
        Prenotazione prenotazioneCasuale = prenotazioni.get(faker.random().nextInt(prenotazioni.size()));

        // ora provo a salvare una prenotazione per una postazione già occupata in quella data
        try {
            prenotazioniService.salvaPrenotazione(new Prenotazione(prenotazioneCasuale.getData(), prenotazioneCasuale.getPostazione(), utenteCasuale));
        } catch (Exception ex) {
            log.error("Non è stato possibile salvare questa prenotazione perché la postazione è già occupata in quella data");
        }

        // ora provo a salvare una prenotazione per una utente già impegnato in quella data
        try {
            prenotazioniService.salvaPrenotazione(new Prenotazione(prenotazioneCasuale.getData(), postazioneCasuale, prenotazioneCasuale.getUtente()));
        } catch (Exception ex) {
            log.error("Non è stato possibile salvare questa prenotazione perché l'utente è già impegnato in quella data");
        }
        
    }
}
