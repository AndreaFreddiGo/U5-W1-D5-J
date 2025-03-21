package andrea_freddi.U5_W1_D5_J.repositories;

import andrea_freddi.U5_W1_D5_J.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UtentiRepository extends JpaRepository<Utente, UUID> {

    // creo delle DERIVED QUERIES per la classe Utente
    Utente findByUsername(String username);

    Utente findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
