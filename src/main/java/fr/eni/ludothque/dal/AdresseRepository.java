package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Adresse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdresseRepository extends MongoRepository<Adresse, String> {
}