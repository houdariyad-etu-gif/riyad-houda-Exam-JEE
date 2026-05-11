package riyad.houda.gestionLocationVehicules.web;

import riyad.houda.gestionLocationVehicules.dtos.LocationDTO;
import riyad.houda.gestionLocationVehicules.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    // GET : Toutes les locations (ADMIN et EMPLOYE)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public List<LocationDTO> getAllLocations() {
        return locationService.getAllLocations();
    }

    // GET : Location par ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public LocationDTO getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    // GET : Locations par véhicule
    @GetMapping("/vehicule/{vehiculeId}")
    public List<LocationDTO> getLocationsByVehicule(@PathVariable Long vehiculeId) {
        return locationService.getLocationsByVehicule(vehiculeId);
    }

    // POST : Créer une location (CLIENT, EMPLOYE, ADMIN)
    @PostMapping
    @PreAuthorize("hasAnyRole('CLIENT', 'EMPLOYE', 'ADMIN')")
    public LocationDTO createLocation(@RequestBody LocationDTO locationDTO) {
        return locationService.createLocation(locationDTO);
    }

    // DELETE : Supprimer une location (ADMIN seulement)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }

    // GET : Calculer prix total
    @GetMapping("/prix")
    public Double calculerPrix(@RequestParam Long vehiculeId, @RequestParam int jours) {
        return locationService.calculerPrixTotal(vehiculeId, jours);
    }
}