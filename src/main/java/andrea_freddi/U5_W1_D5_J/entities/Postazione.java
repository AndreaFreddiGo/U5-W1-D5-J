package andrea_freddi.U5_W1_D5_J.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "postazioni")
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(name = "postazione_id", nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String descrizione;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;
    @Column(nullable = false)
    private int numMaxOccupanti;

    public Postazione(String descrizione, int numMaxOccupanti, Tipo tipo) {
        this.descrizione = descrizione;
        this.numMaxOccupanti = numMaxOccupanti;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Postazione{" +
                "descrizione='" + descrizione + '\'' +
                ", id=" + id +
                ", tipo=" + tipo +
                ", numMaxOccupanti=" + numMaxOccupanti +
                '}';
    }
}
