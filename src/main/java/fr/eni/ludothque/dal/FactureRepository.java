package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Facture;
import fr.eni.ludothque.bo.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FactureRepository extends JpaRepository<Facture, Integer> {
}