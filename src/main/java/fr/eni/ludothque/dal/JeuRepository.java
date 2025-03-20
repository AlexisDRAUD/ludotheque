package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JeuRepository extends JpaRepository<Jeu, Integer> {
}