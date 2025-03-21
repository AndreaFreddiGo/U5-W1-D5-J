package andrea_freddi.U5_W1_D5_J.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

// creo la classe Postazione con i relativi attributi
// uso lombok per generare i getter e i setter e il costruttore vuoto ma non il costruttore con tutti i parametri
// o il toString per evitare conflitti
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

    // creo la relazione OneToMany tra Postazione e Prenotazione
    @OneToMany(mappedBy = "postazione")
    private List<Prenotazione> listaPrenotazioni;

    // creo la relazione ManyToOne tra Postazione e Edificio
    @ManyToOne
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;

    public Postazione(String descrizione, Edificio edificio, int numMaxOccupanti, Tipo tipo) {
        this.descrizione = descrizione;
        this.edificio = edificio;
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
