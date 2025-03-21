package andrea_freddi.U5_W1_D5_J.repositories;

import andrea_freddi.U5_W1_D5_J.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EdificiRepository extends JpaRepository<Edificio, UUID> {

    // creo delle DERIVED QUERIES per la classe Edificio
    Edificio findByNome(String nome);

    boolean existsByNome(String nome);

}
