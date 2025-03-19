package fr.eni.ludothque.bo;

import jakarta.persistence.*;
        import lombok.*;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of= {"rue", "codePostal", "ville"})
@ToString
@Entity
@Table(name = "ADRESSES")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NonNull private String rue;

    @Column(nullable = false, length = 5)
    @NonNull private String codePostal;

    @Column(nullable = false, length = 30)
    @NonNull private String ville;

    @OneToOne(mappedBy = "adresse")
    private Client client;
}

