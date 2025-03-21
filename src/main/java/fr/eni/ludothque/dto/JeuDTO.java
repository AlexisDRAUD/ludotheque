package fr.eni.ludothque.dto;

import fr.eni.ludothque.bo.Jeu;

public class JeuDTO {
    private final Jeu jeu;
    private final int nombreExemplaires;

    public JeuDTO(Jeu jeu, int nombreExemplaires) {
        this.jeu = jeu;
        this.nombreExemplaires = nombreExemplaires;
    }

    // Getters
    public Jeu getJeu() { return jeu; }
    public int getNombreExemplaires() { return nombreExemplaires; }
}