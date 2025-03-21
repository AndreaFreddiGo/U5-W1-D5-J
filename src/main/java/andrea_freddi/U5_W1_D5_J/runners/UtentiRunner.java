package andrea_freddi.U5_W1_D5_J.runners;

import andrea_freddi.U5_W1_D5_J.entities.Utente;
import andrea_freddi.U5_W1_D5_J.services.UtentiService;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// creo un runner in cui richiamo i metodi definiti nel service
@Component
@Slf4j
@Order(1) // definisco l'ordine di esecuzione del runner
public class UtentiRunner implements CommandLineRunner {
    // creo un'istanza del service
    @Autowired
    private UtentiService utentiService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.ITALY);

        List<Utente> nuoviUtenti = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Utente nuovoUtente = new Utente(faker.name().lastName(), faker.internet().emailAddress(), faker.gameOfThrones().character(), faker.name().username());
            nuoviUtenti.add(nuovoUtente);
        }

        // salvo i nuovi utenti nel database
//        try {
//            utentiService.salvaMoltiUtenti(nuoviUtenti);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }


    }
}
