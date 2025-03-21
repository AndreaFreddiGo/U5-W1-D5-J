package andrea_freddi.U5_W1_D5_J.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

// creo la classe Edificio con i relativi attributi
// uso lombok per generare i getter e i setter e il costruttore vuoto ma non il costruttore con tutti i parametri
// o il toString per evitare conflitti
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "edifici")
public class Edificio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(name = "edificio_id", nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String indirizzo;
    @Column(nullable = false)
    private String città;

    // creo la relazione OneToMany tra Edificio e Postazione
    @OneToMany(mappedBy = "edificio")
    private List<Postazione> listaPostazioni;

    public Edificio(String città, String nome, String indirizzo) {
        this.città = città;
        this.nome = nome;
        this.indirizzo = indirizzo;
    }

    @Override
    public String toString() {
        return "Edificio{" +
                "città='" + città + '\'' +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
