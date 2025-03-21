package andrea_freddi.U5_W1_D5_J.repositories;

import andrea_freddi.U5_W1_D5_J.entities.Postazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostazioniRepository extends JpaRepository<Postazione, UUID> {

}
