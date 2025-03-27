package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Client;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
    List<Client> findByNom(String nom);
    void deleteById(@NonNull String id);
}