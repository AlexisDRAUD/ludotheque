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

    @Column(nullable = false, length = 13)
    @NonNull private String codeBarre;

    @Basic(optional = false)
    @NonNull private boolean estLouable;

    @ManyToOne
    @JoinColumn(name = "jeu_id")
    private Jeu jeu;
}