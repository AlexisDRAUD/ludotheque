package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Integer> {
}