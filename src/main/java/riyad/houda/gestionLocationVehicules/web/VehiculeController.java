package riyad.houda.gestionLocationVehicules.web;

import riyad.houda.gestionLocationVehicules.dtos.VehiculeDTO;
import riyad.houda.gestionLocationVehicules.services.VehiculeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final VehiculeService vehiculeService;

    @GetMapping
    public List<VehiculeDTO> getAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    @GetMapping("/available")
    public List<VehiculeDTO> getAvailableVehicules() {
        return vehiculeService.getAvailableVehicules();
    }

    @GetMapping("/{id}")
    public VehiculeDTO getVehiculeById(@PathVariable Long id) {
        return vehiculeService.getVehiculeById(id);
    }

    @GetMapping("/agence/{agenceId}")
    public List<VehiculeDTO> getVehiculesByAgence(@PathVariable Long agenceId) {
        return vehiculeService.getVehiculesByAgence(agenceId);
    }

    @GetMapping("/search")
    public List<VehiculeDTO> searchVehicules(@RequestParam String marque) {
        return vehiculeService.searchVehiculesByMarque(marque);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYE')")
    public VehiculeDTO createVehicule(@RequestBody VehiculeDTO vehiculeDTO) {
        return vehiculeService.createVehicule(vehiculeDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYE')")
    public VehiculeDTO updateVehicule(@PathVariable Long id, @RequestBody VehiculeDTO vehiculeDTO) {
        return vehiculeService.updateVehicule(id, vehiculeDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
    }
}