package fr.eni.ludothque.api;

import fr.eni.ludothque.bll.LocationService;
import fr.eni.ludothque.dto.LocationRequestDTO;
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
}
