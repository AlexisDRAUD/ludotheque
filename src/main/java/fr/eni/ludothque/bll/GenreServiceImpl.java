package fr.eni.ludothque.bll;

import fr.eni.ludothque.bo.Genre;
import fr.eni.ludothque.dal.GenreRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    @NonNull
    private GenreRepository genreRepository;

    @Override
    public void addGenre(Genre genre) {
        genreRepository.save(genre);
    }
}