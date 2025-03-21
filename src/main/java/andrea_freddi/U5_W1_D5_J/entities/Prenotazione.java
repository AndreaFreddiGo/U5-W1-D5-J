package andrea_freddi.U5_W1_D5_J.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(name = "prenotazione_id", nullable = false)
    private UUID id;
    @Column(nullable = false)
    private LocalDate data;

    public Prenotazione(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "data=" + data +
                ", id=" + id +
                '}';
    }
}
