package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Adresse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AdresseRepositoryTest {

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Tester la création d'une adresse")
    public void testCreateAdresse() {
        // Arrange
        Adresse adresse = new Adresse("123 rue du Test", "75001", "Paris");

        // Act
        Adresse savedAdresse = adresseRepository.save(adresse);

        // Assert
        assertNotNull(savedAdresse.getId());
        assertEquals("123 rue du Test", savedAdresse.getRue());
        assertEquals("75001", savedAdresse.getCodePostal());
        assertEquals("Paris", savedAdresse.getVille());
    }

    @Test
    @DisplayName("Tester la recherche d'une adresse par ID")
    public void testFindAdresseById() {
        // Arrange
        Adresse adresse = new Adresse("45 avenue des Fleurs", "69001", "Lyon");
        Adresse savedAdresse = adresseRepository.save(adresse);

        // Act
        Optional<Adresse> foundAdresse = adresseRepository.findById(savedAdresse.getId());

        // Assert
        assertTrue(foundAdresse.isPresent());
        assertEquals("Lyon", foundAdresse.get().getVille());
    }

    @Test
    @DisplayName("Tester la mise à jour d'une adresse")
    public void testUpdateAdresse() {
        // Arrange
        Adresse adresse = new Adresse("7 rue du Marché", "33000", "Bordeaux");
        Adresse savedAdresse = adresseRepository.save(adresse);

        // Act
        savedAdresse.setVille("Bordeaux Métropole");
        savedAdresse.setRue("7 bis rue du Marché");
        Adresse updatedAdresse = adresseRepository.save(savedAdresse);

        // Assert
        assertEquals("Bordeaux Métropole", updatedAdresse.getVille());
        assertEquals("7 bis rue du Marché", updatedAdresse.getRue());
    }

    @Test
    @DisplayName("Tester la suppression d'une adresse")
    public void testDeleteAdresse() {
        // Arrange
        Adresse adresse = new Adresse("9 rue Déserte", "59000", "Lille");
        Adresse savedAdresse = adresseRepository.save(adresse);

        // Act
        adresseRepository.delete(savedAdresse);
        Optional<Adresse> deletedAdresse = adresseRepository.findById(savedAdresse.getId());

        // Assert
        assertFalse(deletedAdresse.isPresent());
    }
}