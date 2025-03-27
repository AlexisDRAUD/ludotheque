package fr.eni.ludothque.bo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "jeu")
public class Exemplaire {

    @Id
    private String id;

    @NonNull private String codeBarre;

    private boolean estLouable;

    @DocumentReference(lazy=true)
    private Jeu jeu;
}