package fr.eni.ludothque.bo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString()
@Entity
@Table(name = "LOCATIONS")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NonNull private LocalDateTime dateDepart;

    @Column
    private LocalDateTime dateRetour;

    @Column
    private float tarifJour;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @NonNull private Client client;

    @ManyToOne
    @JoinColumn(name = "exemplaire_id")
    @NonNull private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "facture_id")
    private Facture facture;
}