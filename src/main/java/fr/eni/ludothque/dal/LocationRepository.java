package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByExemplaireCodeBarreInAndClientId(List<String> codeBarres, Integer clientId);
}