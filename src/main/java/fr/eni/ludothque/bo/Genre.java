package fr.eni.ludothque.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString()
public class Genre {

    @Id
    private String id;

    @NonNull private String libelle;
}