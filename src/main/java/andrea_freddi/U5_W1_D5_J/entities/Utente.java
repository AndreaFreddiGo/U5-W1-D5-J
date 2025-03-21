package andrea_freddi.U5_W1_D5_J.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

// creo la classe Utente con i relativi attributi
// uso lombok per generare i getter e i setter e il costruttore vuoto ma non il costruttore con tutti i parametri
// o il toString per evitare conflitti
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    @Column(name = "utente_id", nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false)
    private String email;

    // creo la relazione OneToMany tra Utente e Prenotazione
    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> listaPrenotazioni;

    public Utente(String cognome, String email, String nome, String username) {
        this.cognome = cognome;
        this.email = email;
        this.nome = nome;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "cognome='" + cognome + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
