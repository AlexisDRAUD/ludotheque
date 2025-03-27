package fr.eni.ludothque.bo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"genres"})
public class Jeu {

    @Id
    private String id;

    @NonNull private String titre;

    @NonNull private String reference;

    private int ageMin;

    private String description;

    private int duree;

    private float tarifJour;

    @DocumentReference(lazy=true)
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