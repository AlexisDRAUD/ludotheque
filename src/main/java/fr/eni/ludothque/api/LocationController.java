package fr.eni.ludothque.api;

import fr.eni.ludothque.bll.LocationService;
import fr.eni.ludothque.dto.LocationRequestDTO;
import fr.eni.ludothque.dto.LocationReturnRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/scan")
    public ResponseEntity<String> addLocationByBarcode(@RequestBody LocationRequestDTO request) {
        locationService.addLocationByBarcode(request.getClientId(), request.getCodeBarre());
        return ResponseEntity.ok("Location enregistrée avec succès.");
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnLocations(@RequestBody LocationReturnRequestDTO request) {
        locationService.returnLocations(request.getCodeBarres(), request.getClientId());
        return ResponseEntity.ok("Retour des locations effectué et facture générée.");
    }
}
