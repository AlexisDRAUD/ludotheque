package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Genre;
import fr.eni.ludothque.bo.Jeu;
import fr.eni.ludothque.dto.JeuDTO;

import java.util.List;

public interface JeuService {

    void addJeu(Jeu jeu);
    void removeJeu(Jeu jeu);
    Jeu findJeuById(int id);
    void updateJeu(Jeu jeu);
    void updateJeuGenre(int id, Genre genre);
    public List<JeuDTO> getAllJeux(String titre, Genre genre);
}