package riyad.houda.gestionLocationVehicules.web;

import riyad.houda.gestionLocationVehicules.dtos.AgenceDTO;
import riyad.houda.gestionLocationVehicules.services.AgenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final AgenceService agenceService;

    @GetMapping
    public List<AgenceDTO> getAllAgences() {
        return agenceService.getAllAgences();
    }

    @GetMapping("/{id}")
    public AgenceDTO getAgenceById(@PathVariable Long id) {
        return agenceService.getAgenceById(id);
    }

    @GetMapping("/ville/{ville}")
    public List<AgenceDTO> getAgencesByVille(@PathVariable String ville) {
        return agenceService.getAgencesByVille(ville);
    }
}