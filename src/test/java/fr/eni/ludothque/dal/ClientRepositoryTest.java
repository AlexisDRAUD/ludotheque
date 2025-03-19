package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Adresse;
import fr.eni.ludothque.bo.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Tester la création d'un client sans adresse")
    public void testCreateClientSansAdresse() {
        // Arrange
        Client client = new Client("Dupont", "Jean", "jean.dupont@example.com", "0123456789");

        // Act
        Client savedClient = clientRepository.save(client);

        // Assert
        assertNotNull(savedClient.getId());
        assertEquals("Dupont", savedClient.getNom());
        assertEquals("jean.dupont@example.com", savedClient.getEmail());
    }

    @Test
    @DisplayName("Tester la création d'un client avec adresse")
    public void testCreateClientAvecAdresse() {
        // Arrange
        Adresse adresse = new Adresse("1 rue de la Paix", "75001", "Paris");
        Client client = new Client("Martin", "Sophie", "sophie.martin@example.com", "0123456788");
        client.setAdresse(adresse);

        // Act
        Client savedClient = clientRepository.save(client);

        // Assert
        assertNotNull(savedClient.getId());
        assertNotNull(savedClient.getAdresse());
        assertNotNull(savedClient.getAdresse().getId());
        assertEquals("Paris", savedClient.getAdresse().getVille());
    }
}