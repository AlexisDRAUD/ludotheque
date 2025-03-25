package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.*;
import fr.eni.ludothque.dal.ExemplaireRepository;
import fr.eni.ludothque.dal.FactureRepository;
import fr.eni.ludothque.dal.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    private ExemplaireRepository exemplaireRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private FactureRepository factureRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    private Client client;
    private Exemplaire exemplaire;
    private Location location;

    @BeforeEach
    void setUp() {
        Adresse adresse = new Adresse("7 rue Colette Magny", "44100", "Nantes");
        client = new Client("n1", "p1", "e1", "tel1", adresse);
        exemplaire = new Exemplaire("1234567890123", true);
        location = new Location(LocalDateTime.now(), client, exemplaire);
    }


    @Test
    void testAddLocation() {
        when(exemplaireRepository.save(any(Exemplaire.class))).thenReturn(exemplaire);
        when(locationRepository.save(any(Location.class))).thenReturn(location);

        locationService.addLocation(client, exemplaire);

        verify(locationRepository, times(1)).save(any(Location.class));
        verify(exemplaireRepository, times(1)).save(any(Exemplaire.class));
    }

    @Test
    void testEndLocation() {
        when(exemplaireRepository.save(any(Exemplaire.class))).thenReturn(exemplaire);
        when(factureRepository.save(any(Facture.class))).thenReturn(new Facture(LocalDateTime.now(), List.of(location)));
        when(locationRepository.save(any(Location.class))).thenReturn(location);

        locationService.endLocations(List.of(location));

        verify(factureRepository, times(1)).save(any(Facture.class));
        verify(exemplaireRepository, times(1)).save(any(Exemplaire.class));
        verify(locationRepository, times(1)).save(any(Location.class));

        assertTrue(location.getExemplaire().isEstLouable());
    }
}