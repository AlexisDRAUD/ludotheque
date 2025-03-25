package fr.eni.ludothque.api;

import fr.eni.ludothque.bll.GenreService;
import fr.eni.ludothque.bo.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/add")
    public ResponseEntity<Genre> addGenre(@RequestBody Genre genre) {
        genreService.addGenre(genre);
        return ResponseEntity.ok().build();
    }
}
