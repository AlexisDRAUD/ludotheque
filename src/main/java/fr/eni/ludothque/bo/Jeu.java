package fr.eni.ludothque.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "JEUX")
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NonNull private String titre;

    @Column(nullable = false, length = 20)
    @NonNull private String reference;

    @Column
    private Integer ageMin;

    @Column(length = 100)
    private String description;

    @Column
    private Integer duree;

    @Column(nullable = false)
    @NonNull private Double tarifJour;

}

