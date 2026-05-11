package riyad.houda.gestionLocationVehicules.services;

import riyad.houda.gestionLocationVehicules.dtos.LocationDTO;
import java.util.List;

public interface LocationService {

    List<LocationDTO> getAllLocations();

    LocationDTO getLocationById(Long id);

    List<LocationDTO> getLocationsByVehicule(Long vehiculeId);

    LocationDTO createLocation(LocationDTO locationDTO);

    void deleteLocation(Long id);

    Double calculerPrixTotal(Long vehiculeId, int nombreJours);
}