package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {
}