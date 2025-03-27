package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Facture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FactureRepository extends MongoRepository<Facture, String> {
}