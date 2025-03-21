package andrea_freddi.U5_W1_D5_J.runners;

import andrea_freddi.U5_W1_D5_J.entities.Edificio;
import andrea_freddi.U5_W1_D5_J.services.EdificiService;
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
@Order(2) // definisco l'ordine di esecuzione del runner
public class EdificiRunner implements CommandLineRunner {
    // creo un'istanza del service
    @Autowired
    private EdificiService edificiService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.ITALY);

        List<Edificio> nuoviEdifici = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Edificio nuovoEdificio = new Edificio(faker.address().city(), faker.letterify("?").toUpperCase() + faker.address().buildingNumber(), faker.address().streetAddress());
            nuoviEdifici.add(nuovoEdificio);
        }

        // salvo i nuovi edifici nel database
//        try {
//            edificiService.salvaMoltiEdifici(nuoviEdifici);
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//        }

    }
}
