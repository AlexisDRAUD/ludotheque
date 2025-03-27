package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Client;
import fr.eni.ludothque.bo.Exemplaire;
import fr.eni.ludothque.bo.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
    List<Location> findByExemplaireInAndClient(List<Exemplaire> exemplaires, Client client);
}