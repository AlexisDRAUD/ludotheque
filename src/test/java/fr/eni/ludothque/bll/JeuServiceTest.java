package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Genre;
import fr.eni.ludothque.bo.Jeu;
import fr.eni.ludothque.dal.ExemplaireRepository;
import fr.eni.ludothque.dal.JeuRepository;
import fr.eni.ludothque.dto.JeuDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JeuServiceTest {

    @BeforeEach
    void setUp() {
        genreAventure = new Genre("Aventure");
        genreCoop = new Genre("Coop");
        jeu1 = new Jeu("t1", "r1", 10.0f);
        jeu1.addGenre(genreAventure);
        jeu2 = new Jeu("t2", "r2", 10.0f);
        jeu2.addGenre(genreCoop);
    }

    @Autowired
    private JeuService jeuService;

    @MockitoBean
    private JeuRepository jeuRepository;

    @MockitoBean
    private ExemplaireRepository exemplaireRepository;

    private Jeu jeu1, jeu2;
    private Genre genreAventure;
    private Genre genreCoop;


    @Test
    @DisplayName("Ajout d'un jeu cas positif")
    public void ajoutJeuCasPositif() {
        //Arrange
        Genre genre = new Genre("genre1");
        List<Genre> genres = new ArrayList<>();
        genres.add(genre);
        Jeu jeu = new Jeu("t1", "r1", 10.0f);
        jeu.setGenres(genres);
        doAnswer(invocation -> {
                jeu.setId(9);
                return jeu;
        }).when(jeuRepository).save(jeu);

        //Act
        jeuService.addJeu(jeu);

        //Assert
        assertNotNull(jeu.getId());
        assertEquals(9, jeu.getId());
    }

    @Test
    void testGetAllJeux() {
        when(jeuRepository.findAll()).thenReturn(Arrays.asList(jeu1, jeu2));
        when(exemplaireRepository.findByJeu(jeu1)).thenReturn(List.of());
        when(exemplaireRepository.findByJeu(jeu2)).thenReturn(List.of());

        List<JeuDTO> result = jeuService.getAllJeux(null, null);
        assertEquals(2, result.size());
    }

    @Test
    void testGetAllJeuxByTitle() {
        when(jeuRepository.findAll()).thenReturn(Arrays.asList(jeu1, jeu2));
        when(exemplaireRepository.findByJeu(jeu1)).thenReturn(List.of());
        when(exemplaireRepository.findByJeu(jeu2)).thenReturn(List.of());

        List<JeuDTO> result = jeuService.getAllJeux("t1", null);
        assertEquals(1, result.size());
        assertEquals("t1", result.get(0).getJeu().getTitre());
    }

    @Test
    void testGetAllJeuxByGenre() {
        when(jeuRepository.findAll()).thenReturn(Arrays.asList(jeu1, jeu2));
        when(exemplaireRepository.findByJeu(jeu1)).thenReturn(List.of());
        when(exemplaireRepository.findByJeu(jeu2)).thenReturn(List.of());

        List<JeuDTO> result = jeuService.getAllJeux(null, genreAventure);
        assertEquals(1, result.size());
    }
}