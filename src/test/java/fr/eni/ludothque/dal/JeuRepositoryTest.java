package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Genre;
import fr.eni.ludothque.bo.Jeu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JeuRepositoryTest {

    @Autowired
    private JeuRepository jeuRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Tester la création d'un jeu sans genre")
    public void testCreateJeuSansGenre() {
        // Arrange
        Jeu jeu = new Jeu("Monopoly", "ref1", 10.0f);
        jeu.setAgeMin(18);
        jeu.setDescription("Jeu de capitaliste");
        jeu.setDuree(120);

        // Act
        Jeu savedJeu = jeuRepository.save(jeu);

        // Assert
        assertNotNull(savedJeu.getId());
        assertEquals("Monopoly", savedJeu.getTitre());
        assertEquals("ref1", savedJeu.getReference());
    }

    @Test
    @DisplayName("Tester la création d'un jeu avec genre")
    public void testCreateJeuAvecGenre() {
        // Arrange
        Genre genre = new Genre("Stratégie");

        // Sauvegarder d'abord le genre
        Genre savedGenre = genreRepository.save(genre);

        Jeu jeu = new Jeu("Risk", "ref2", 15.0f);
        jeu.getGenres().add(savedGenre);  // Utiliser le genre sauvegardé

        // Act
        Jeu savedJeu = jeuRepository.save(jeu);

        // Assert
        assertNotNull(savedJeu.getId());
        assertNotNull(savedJeu.getGenres());
        assertEquals(1, savedJeu.getGenres().size());
    }
}