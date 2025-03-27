package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.*;
import fr.eni.ludothque.dal.ClientRepository;
import fr.eni.ludothque.dal.ExemplaireRepository;
import fr.eni.ludothque.dal.FactureRepository;
import fr.eni.ludothque.dal.LocationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    @NonNull
    private ExemplaireRepository exemplaireRepository;
    @NonNull
    private LocationRepository locationRepository;
    @NonNull
    private FactureRepository factureRepository;
    @NonNull
    private ClientRepository clientRepository;

    @Override
    public void addLocation(Client client, Exemplaire exemplaire) {
        if (exemplaire.isEstLouable()) {
            Location location = new Location(LocalDateTime.now(), client, exemplaire);
            locationRepository.save(location);
            exemplaire.setEstLouable(false);
            exemplaireRepository.save(exemplaire);
        }
    }

    @Override
    public void addLocationByBarcode(String clientId, String codeBarre) {
        Exemplaire exemplaire = exemplaireRepository.findByCodeBarre(codeBarre)
                .orElseThrow(() -> new RuntimeException("Exemplaire non trouvé"));

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        if (!exemplaire.isEstLouable()) {
            throw new RuntimeException("Cet exemplaire n'est pas disponible à la location.");
        }

        Location location = new Location(LocalDateTime.now(), client, exemplaire);
        locationRepository.save(location);
        exemplaire.setEstLouable(false);
        exemplaireRepository.save(exemplaire);
    }

    @Override
    public void returnLocations(List<String> codeBarres, String clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        List<Exemplaire> exemplaires = exemplaireRepository.findByCodeBarreIn(codeBarres);
        if (exemplaires.isEmpty()) {
            throw new RuntimeException("Aucun exemplaire trouvé pour ces code-barres.");
        }

        List<Location> locations = locationRepository.findByExemplaireInAndClient(exemplaires, client);
        if (locations.isEmpty()) {
            throw new RuntimeException("Aucune location trouvée pour ces code-barres.");
        }

        LocalDateTime now = LocalDateTime.now();

        for (Location location : locations) {
            location.setDateRetour(now);
        }
        locationRepository.saveAll(locations);

        Facture facture = new Facture(now, locations);
        factureRepository.save(facture);

        for (Location location : locations) {
            location.setFacture(facture);
        }
        locationRepository.saveAll(locations);

        for (Exemplaire exemplaire : exemplaires) {
            exemplaire.setEstLouable(true);
        }
        exemplaireRepository.saveAll(exemplaires);
    }

}