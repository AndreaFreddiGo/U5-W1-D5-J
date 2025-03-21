package andrea_freddi.U5_W1_D5_J.repositories;

import andrea_freddi.U5_W1_D5_J.entities.Postazione;
import andrea_freddi.U5_W1_D5_J.entities.Prenotazione;
import andrea_freddi.U5_W1_D5_J.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {

    // creo delle DERIVED QUERIES per la classe Prenotazione
    Prenotazione findByPostazioneId(UUID postazioneId);

    Prenotazione findByUtenteId(UUID utenteId);

    boolean existsByPostazioneId(UUID postazioneId);

    boolean existsByUtenteId(UUID utenteId);

    // questa query mi serve per controllare se una postazione è già prenotata per una data
    boolean existsByPostazioneAndData(Postazione postazione, LocalDate data);

    // questa query mi serve per controllare se un utente ha già prenotato una postazione per una data
    boolean existsByUtenteAndData(Utente utente, LocalDate data);

    // cerco tutte le prenotazioni di un utente
    List<Prenotazione> findByUtente(Utente utente);

    // cerco tutte le prenotazioni di una postazione
    List<Prenotazione> findByPostazione(Postazione postazione);
}
