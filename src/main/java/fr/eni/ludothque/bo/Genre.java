package fr.eni.ludothque.bo;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "jeux")
@Entity
@Table(name = "GENRES")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NonNull private String libelle;

    @ManyToMany(mappedBy = "genres")
    private Set<Jeu> jeux = new HashSet<>();
}