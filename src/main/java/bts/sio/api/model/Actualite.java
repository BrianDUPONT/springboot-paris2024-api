package bts.sio.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "actualite")
public class Actualite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titre")
    private String titre;

    @Column(name="contenu")
    private String contenu;

    @Column(name="date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "epreuve_id")
    private Epreuve epreuve;

    @ManyToOne
    @JoinColumn(name = "sport_id")
    private Sport sport;

}
