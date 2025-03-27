package fr.eni.ludothque.bo;

import jakarta.persistence.*;
        import lombok.*;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of= {"rue", "codePostal", "ville"})
@ToString
public class Adresse {

    @Id
    private String id;

    @NonNull private String rue;

    @NonNull private String codePostal;

    @NonNull private String ville;

}

