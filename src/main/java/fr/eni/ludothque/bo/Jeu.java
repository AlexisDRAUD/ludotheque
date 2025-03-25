package fr.eni.ludothque.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"genres"})
@Entity
@Table(name = "JEUX")
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NonNull private String titre;

    @Column(nullable = false, length = 13, unique = true)
    @NonNull private String reference;

    @Column
    private int ageMin;

    @Column(length = 100)
    private String description;

    @Column
    private int duree;

    @Column(nullable = false)
    @NonNull private float tarifJour;

    @NotEmpty
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "JEUX_GENRES",
            joinColumns = @JoinColumn(name = "jeu_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @NonNull private List<Genre> genres = new ArrayList<>();

    public Jeu(String titre, String reference, int ageMin, String description, int duree, float tarifJour) {
        this.titre = titre;
        this.reference = reference;
        this.ageMin = ageMin;
        this.description = description;
        this.duree = duree;
        this.tarifJour = tarifJour;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }
}