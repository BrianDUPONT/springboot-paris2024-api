package bts.sio.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "epreuve")
public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom")
    private String nom;

}
