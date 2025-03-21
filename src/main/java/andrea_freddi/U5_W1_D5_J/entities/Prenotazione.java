package andrea_freddi.U5_W1_D5_J.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

// creo la classe Prenotazione con i relativi attributi
// uso lombok per generare i getter e i setter e il costruttore vuoto ma non il costruttore con tutti i parametri
// o il toString per evitare conflitti
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

    // creo la relazione ManyToOne tra Prenotazione e Utente
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    // creo la relazione ManyToOne tra Prenotazione e Postazione
    @ManyToOne
    @JoinColumn(name = "postazione_id", nullable = false)
    private Postazione postazione;

    public Prenotazione(LocalDate data, Postazione postazione, Utente utente) {
        this.data = data;
        this.postazione = postazione;
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "data=" + data +
                ", id=" + id +
                '}';
    }
}
