package fr.eni.ludothque.bo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString()
public class Location {

    @Id
    private String id;

    @NonNull private LocalDateTime dateDepart;

    private LocalDateTime dateRetour;

    private float tarifJour;

    @DocumentReference(lazy=true)
    @NonNull private Client client;

    @DocumentReference(lazy=true)
    @NonNull private Exemplaire exemplaire;

    @DocumentReference(lazy=true)
    private Facture facture;
}