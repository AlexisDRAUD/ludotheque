package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Exemplaire;
import fr.eni.ludothque.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    List<Exemplaire> findByJeu(Jeu jeu);
    Optional<Exemplaire> findByCodeBarre(String codeBarre);
}