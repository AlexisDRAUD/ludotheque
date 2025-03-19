package fr.eni.ludothque.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of= {"email"})
@ToString
@Entity
@Table(name = "CLIENTS")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NonNull private String nom;

    @Column(nullable = false, length = 50)
    @NonNull private String prenom;

    @Column(nullable = false, length = 20, unique = true)
    @NonNull private String email;

    @Column(nullable = false, length = 10, unique = true)
    @NonNull private String noTel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Adresse adresse;
}
