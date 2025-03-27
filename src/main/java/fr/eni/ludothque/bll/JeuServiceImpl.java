package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Genre;
import fr.eni.ludothque.bo.Jeu;
import fr.eni.ludothque.dal.ExemplaireRepository;
import fr.eni.ludothque.dal.GenreRepository;
import fr.eni.ludothque.dal.JeuRepository;
import fr.eni.ludothque.dto.JeuDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JeuServiceImpl implements JeuService {

    private final ExemplaireRepository exemplaireRepository;
    @NonNull
    private JeuRepository jeuRepository;
    @NonNull
    private GenreRepository genreRepository;

    @Override
    public void addJeu(Jeu jeu) {
        jeuRepository.save(jeu);
    }

    @Override
    public void removeJeu(Jeu jeu) {
        jeuRepository.delete(jeu);
    }

    @Override
    public Jeu findJeuById(String id) {
        Optional<Jeu> jeu = jeuRepository.findById(id);
        return jeu.orElse(null);
    }

    @Override
    public void updateJeu(Jeu jeu) {
        jeuRepository.save(jeu);
    }

    @Override
    public void updateJeuGenre(String id, Genre genre) {
        Optional<Jeu> jeu = jeuRepository.findById(id);
        jeu.ifPresent((j) -> {
            j.addGenre(genre);
            jeuRepository.save(j);
        });
    }

    @Override
    public List<JeuDTO> getAllJeux(String titre, Genre genre) {
        List<Jeu> jeux = jeuRepository.findAll();

        if (titre != null && !titre.isEmpty()) {
            jeux = jeux.stream()
                    .filter(j -> j.getTitre().toLowerCase().contains(titre.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (genre != null) {
            jeux = jeux.stream()
                    .filter(j -> j.getGenres().contains(genre))
                    .collect(Collectors.toList());
        }

        return jeux.stream().map(j -> new JeuDTO(j, exemplaireRepository.findByJeu(j).size())).toList();
    }
}