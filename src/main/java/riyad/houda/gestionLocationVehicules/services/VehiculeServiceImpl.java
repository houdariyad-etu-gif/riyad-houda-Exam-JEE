package riyad.houda.gestionLocationVehicules.services;

import riyad.houda.gestionLocationVehicules.dtos.VehiculeDTO;
import riyad.houda.gestionLocationVehicules.entities.Vehicule;
import riyad.houda.gestionLocationVehicules.enums.VehiculeStatus;
import riyad.houda.gestionLocationVehicules.mappers.VehiculeMapper;
import riyad.houda.gestionLocationVehicules.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VehiculeServiceImpl implements VehiculeService {

    private final VehicleRepository vehiculeRepository;
    private final VehiculeMapper vehiculeMapper;

    @Override
    public List<VehiculeDTO> getAllVehicules() {
        return vehiculeRepository.findAll().stream()
                .map(vehiculeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehiculeDTO getVehiculeById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec id: " + id));
        return vehiculeMapper.toDTO(vehicule);
    }

    @Override
    public List<VehiculeDTO> getAvailableVehicules() {
        return vehiculeRepository.findByStatut(VehiculeStatus.DISPONIBLE).stream()
                .map(vehiculeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehiculeDTO> getVehiculesByAgence(Long agenceId) {
        return vehiculeRepository.findByAgenceId(agenceId).stream()
                .map(vehiculeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehiculeDTO> searchVehiculesByMarque(String marque) {
        return vehiculeRepository.findByMarqueContainingIgnoreCase(marque).stream()
                .map(vehiculeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehiculeDTO createVehicule(VehiculeDTO vehiculeDTO) {
        throw new UnsupportedOperationException("À implémenter");
    }

    @Override
    public VehiculeDTO updateVehicule(Long id, VehiculeDTO vehiculeDTO) {
        throw new UnsupportedOperationException("À implémenter");
    }

    @Override
    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    @Override
    public List<VehiculeDTO> getAllVehicles() {
        return List.of();
    }

    @Override
    public Vehicule getVehicleById(Long id) {
        return null;
    }

    @Override
    public VehiculeDTO createVehicle(VehiculeDTO vehicleDTO) {
        return null;
    }

    @Override
    public VehiculeDTO updateVehicle(Long id, VehiculeDTO vehicleDTO) {
        return null;
    }

    @Override
    public void deleteVehicle(Long id) {

    }

    @Override
    public List<VehiculeDTO> getAvailableVehicles() {
        return List.of();
    }

    @Override
    public List<VehiculeDTO> getVehiclesByAgence(Long agenceId) {
        return List.of();
    }

    @Override
    public List<VehiculeDTO> searchVehiclesByMarque(String marque) {
        return List.of();
    }
}