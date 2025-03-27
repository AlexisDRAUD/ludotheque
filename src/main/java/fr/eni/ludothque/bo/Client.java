package fr.eni.ludothque.bo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of= {"email"})
@ToString
public class Client {

    @Id
    private String id;

    @NonNull private String nom;

    @NonNull private String prenom;

    @NonNull private String email;

    @NonNull private String noTel;

    @DocumentReference
    @NonNull private Adresse adresse;
}
