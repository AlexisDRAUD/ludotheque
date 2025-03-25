package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Client;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByNom(String nom);
    void deleteById(@NonNull Integer id);
}