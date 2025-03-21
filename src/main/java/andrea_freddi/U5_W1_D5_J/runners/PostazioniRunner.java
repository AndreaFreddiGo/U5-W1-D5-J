package andrea_freddi.U5_W1_D5_J.runners;

import andrea_freddi.U5_W1_D5_J.entities.Edificio;
import andrea_freddi.U5_W1_D5_J.entities.Postazione;
import andrea_freddi.U5_W1_D5_J.entities.Tipo;
import andrea_freddi.U5_W1_D5_J.services.EdificiService;
import andrea_freddi.U5_W1_D5_J.services.PostazioniService;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

// creo un runner in cui richiamo i metodi definiti nel service
@Component
@Slf4j
@Order(3) // definisco l'ordine di esecuzione del runner
public class PostazioniRunner implements CommandLineRunner {
    // creo un'istanza del service
    @Autowired
    private PostazioniService postazioniService;
    // creo anche l'istanza del service per gli edifici perché mi servirà per creare le postazioni
    @Autowired
    private EdificiService edificiService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.ITALY);

        // cerco in modo random di scegliere il tipo di postazione
        List<String> nomiPostazione = new ArrayList<>(Arrays.asList("Area", "Spazio", "Postazione", "Sala"));
        Tipo tipo = Tipo.values()[faker.random().nextInt(Tipo.values().length)];

        // scelgo un nome casuale
        String descrizione = nomiPostazione.get(faker.random().nextInt(nomiPostazione.size()));

        // recupero un edificio casuale
        List<Edificio> edifici = edificiService.recuperaEdifici();
        Edificio edificioCasuale = edifici.get(faker.random().nextInt(edifici.size()));

        List<Postazione> nuovePostazioni = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Postazione nuovaPostazione = new Postazione(descrizione + " " + faker.job().field(), edificioCasuale, faker.random().nextInt(20, 100), tipo);
            nuovePostazioni.add(nuovaPostazione);
        }

        // salvo le nuove postazioni
//        try {
//            postazioniService.salvaMoltePostazioni(nuovePostazioni);
//        } catch (Exception ex) {
//            log.error(ex.getMessage());
//        }

        // recupero tutte le città in cui ci sono edifici in modo da poterne inserire una nel metodo sottostante
        List<String> città = edificiService.recuperaEdifici().stream().map(Edificio::getCittà).distinct().toList();

        // recupero le postazioni in base a tipo e città
        List<Postazione> postazioni = postazioniService.trovaPostazioniPerTipoECittà(tipo, città.get(faker.random().nextInt(città.size())));
        if (postazioni.isEmpty()) {
            log.error("Nessuna postazione trovata con i parametri specificati");
        } else {
            postazioni.forEach(System.out::println);
        }

    }
}
