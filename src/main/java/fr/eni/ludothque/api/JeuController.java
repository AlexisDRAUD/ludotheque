package fr.eni.ludothque.api;

import fr.eni.ludothque.bll.JeuService;
import fr.eni.ludothque.bo.Exemplaire;
import fr.eni.ludothque.bo.Jeu;
import fr.eni.ludothque.dto.JeuDTO;
import fr.eni.ludothque.dto.JeuRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jeux")
@RequiredArgsConstructor
public class JeuController {

    private final JeuService jeuService;

    @GetMapping
    public List<JeuDTO> getAllJeux() {
        return jeuService.getAllJeux(null, null);
    }

    @GetMapping("/{id}")
    public Jeu getJeuById(@PathVariable String id) {
        return jeuService.findJeuById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Jeu> addJeu(@RequestBody Jeu jeu) {
        jeuService.addJeu(jeu);

        return ResponseEntity.ok().build();
    }
}
