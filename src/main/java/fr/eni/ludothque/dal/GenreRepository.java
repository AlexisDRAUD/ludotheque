package fr.eni.ludothque.dal;

import fr.eni.ludothque.bo.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}