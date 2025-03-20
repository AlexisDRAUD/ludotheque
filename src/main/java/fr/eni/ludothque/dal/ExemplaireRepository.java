package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
}