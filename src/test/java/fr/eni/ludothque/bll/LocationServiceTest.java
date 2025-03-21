package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.*;
import fr.eni.ludothque.dal.FactureRepository;
import fr.eni.ludothque.dal.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private FactureRepository factureRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    private Client client;
    private Exemplaire exemplaire;

    @BeforeEach
    void setUp() {
        Adresse adresse = new Adresse("7 rue Colette Magny", "44100", "Nantes");
        client = new Client("n1", "p1", "e1", "tel1", adresse);
        exemplaire = new Exemplaire("1234567890123", true);
    }

    @Test
    void testAddLocation() {
        doAnswer(invocation -> {
            Location location = invocation.getArgument(0);
            location.setId(1);
            return location;
        }).when(locationRepository).save(any(Location.class));

        doAnswer(invocation -> {
            Facture facture = invocation.getArgument(0);
            facture.setId(1);
            return facture;
        }).when(factureRepository).save(any(Facture.class));

        locationService.addLocation(client, exemplaire);

        verify(locationRepository, times(1)).save(any(Location.class));
        verify(factureRepository, times(1)).save(any(Facture.class));
    }
}