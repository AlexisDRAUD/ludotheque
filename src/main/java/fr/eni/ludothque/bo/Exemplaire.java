package fr.eni.ludothque.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = "jeu")
@Entity
@Table(name = "EXEMPLAIRES")
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NonNull private String codeBarre;

    @Column(nullable = false)
    private boolean estLouable = true;

    @ManyToOne
    @JoinColumn(name = "jeu_id")
    private Jeu jeu;
}