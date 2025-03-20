package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Tester la création d'une genre")
    public void testCreateGenre() {
        // Arrange
        Genre genre = new Genre("Coop");

        // Act
        Genre savedGenre = genreRepository.save(genre);

        // Assert
        assertNotNull(savedGenre.getId());
        assertEquals("Coop", savedGenre.getLibelle());
    }

    @Test
    @DisplayName("Tester la recherche d'un genre par ID")
    public void testFindGenreById() {
        // Arrange
        Genre genre = new Genre("Coop");
        Genre savedGenre = genreRepository.save(genre);

        // Act
        Optional<Genre> foundGenre = genreRepository.findById(savedGenre.getId());

        // Assert
        assertTrue(foundGenre.isPresent());
        assertEquals("Coop", foundGenre.get().getLibelle());
    }

    @Test
    @DisplayName("Tester la suppression d'une genre")
    public void testDeleteGenre() {
        // Arrange
        Genre genre = new Genre("Coop");
        Genre savedGenre = genreRepository.save(genre);

        // Act
        genreRepository.delete(savedGenre);
        Optional<Genre> deletedGenre = genreRepository.findById(savedGenre.getId());

        // Assert
        assertFalse(deletedGenre.isPresent());
    }
}