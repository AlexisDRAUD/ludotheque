package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.*;
import fr.eni.ludothque.dal.FactureRepository;
import fr.eni.ludothque.dal.LocationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    @NonNull
    private LocationRepository locationRepository;
    @NonNull
    private FactureRepository factureRepository;

    @Override
    public void addLocation(Client client, Exemplaire exemplaire) {
        if (exemplaire.isEstLouable()) {
            Location location = new Location(LocalDateTime.now(), client, exemplaire);
            locationRepository.save(location);
            Facture facture = new Facture(location);
            factureRepository.save(facture);
        }
    }
}