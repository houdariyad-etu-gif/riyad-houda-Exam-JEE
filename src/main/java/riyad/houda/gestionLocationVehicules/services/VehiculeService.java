package riyad.houda.gestionLocationVehicules.services;

import riyad.houda.gestionLocationVehicules.dtos.VehiculeDTO;
import riyad.houda.gestionLocationVehicules.entities.Vehicule;

import java.util.List;

public interface VehiculeService {
    List<VehiculeDTO> getAllVehicules();

    VehiculeDTO getVehiculeById(Long id);

    List<VehiculeDTO> getAvailableVehicules();

    List<VehiculeDTO> getVehiculesByAgence(Long agenceId);

    List<VehiculeDTO> searchVehiculesByMarque(String marque);

    VehiculeDTO createVehicule(VehiculeDTO vehiculeDTO);

    VehiculeDTO updateVehicule(Long id, VehiculeDTO vehiculeDTO);

    void deleteVehicule(Long id);

    List<VehiculeDTO> getAllVehicles();
    Vehicule getVehicleById(Long id);
    VehiculeDTO createVehicle(VehiculeDTO vehicleDTO);
    VehiculeDTO updateVehicle(Long id, VehiculeDTO vehicleDTO);
    void deleteVehicle(Long id);
    List<VehiculeDTO> getAvailableVehicles();
    List<VehiculeDTO> getVehiclesByAgence(Long agenceId);
    List<VehiculeDTO> searchVehiclesByMarque(String marque);
}