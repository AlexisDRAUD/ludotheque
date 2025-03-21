package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.*;
import fr.eni.ludothque.dal.ExemplaireRepository;
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
    private ExemplaireRepository exemplaireRepository;
    @NonNull
    private LocationRepository locationRepository;
    @NonNull
    private FactureRepository factureRepository;

    @Override
    public void addLocation(Client client, Exemplaire exemplaire) {
        if (exemplaire.isEstLouable()) {
            Location location = new Location(LocalDateTime.now(), client, exemplaire);
            locationRepository.save(location);
            exemplaire.setEstLouable(false);
            exemplaireRepository.save(exemplaire);
        }
    }

    public void endLocation(Location location) {
        LocalDateTime now = LocalDateTime.now();
        Facture facture = new Facture(now, location);
        factureRepository.save(facture);
        Exemplaire exemplaire = location.getExemplaire();
        exemplaire.setEstLouable(true);
        exemplaireRepository.save(exemplaire);
        location.setDateRetour(now);
        locationRepository.save(location);
    }
}