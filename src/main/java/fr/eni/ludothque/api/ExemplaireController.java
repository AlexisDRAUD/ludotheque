package fr.eni.ludothque.api;

import fr.eni.ludothque.bll.ExemplaireService;
import fr.eni.ludothque.bo.Exemplaire;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exemplaires")
@RequiredArgsConstructor
public class ExemplaireController {

    private final ExemplaireService exemplaireService;

    @PostMapping("/add")
    public ResponseEntity<Exemplaire> addExemplaire(@RequestBody Exemplaire exemplaire) {
        exemplaireService.addExemplaire(exemplaire);
        return ResponseEntity.ok().build();
    }
}
