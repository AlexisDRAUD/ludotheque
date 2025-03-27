package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Jeu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JeuRepository extends MongoRepository<Jeu, String> {
}