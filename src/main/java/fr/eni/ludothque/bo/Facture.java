package fr.eni.ludothque.bo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString()
public class Facture {

    @Id
    private String id;

    @NonNull private LocalDateTime datePaiment;

    @DocumentReference(lazy=true)
    @NonNull private List<Location> locations = new ArrayList<>();

    public Facture(@NonNull LocalDateTime now, @NonNull List<Location> locations) {
        this.datePaiment = now;
        this.locations = locations;
    }
}