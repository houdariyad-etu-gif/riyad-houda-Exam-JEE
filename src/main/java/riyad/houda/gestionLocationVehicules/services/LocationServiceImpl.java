package riyad.houda.gestionLocationVehicules.services;

import riyad.houda.gestionLocationVehicules.dtos.LocationDTO;
import riyad.houda.gestionLocationVehicules.entities.Location;
import riyad.houda.gestionLocationVehicules.entities.Vehicule;
import riyad.houda.gestionLocationVehicules.enums.VehiculeStatus;
import riyad.houda.gestionLocationVehicules.mappers.VehiculeMapper;
import riyad.houda.gestionLocationVehicules.repositories.LocationRepository;
import riyad.houda.gestionLocationVehicules.repositories.VehiculeRepository;
import riyad.houda.gestionLocationVehicules.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final VehiculeRepository vehiculeRepository;
    private final VehiculeMapper vehiculeMapper;

    @Override
    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(vehiculeMapper::toLocationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LocationDTO getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location non trouvée avec id: " + id));
        return vehiculeMapper.toLocationDTO(location);
    }

    @Override
    public List<LocationDTO> getLocationsByVehicule(Long vehiculeId) {
        return locationRepository.findByVehiculeId(vehiculeId).stream()
                .map(vehiculeMapper::toLocationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        Vehicule vehicle = vehiculeRepository.findById(locationDTO.getVehiculeId())
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));

        if (vehicle.getStatut() != VehiculeStatus.DISPONIBLE) {
            throw new RuntimeException("Le véhicule n'est pas disponible pour location");
        }

        Location location = new Location();
        location.setDateDebut(locationDTO.getDateDebut());
        location.setDateFin(locationDTO.getDateFin());
        location.setVehicule(vehicle);

        long nombreJours = ChronoUnit.DAYS.between(
                locationDTO.getDateDebut(),
                locationDTO.getDateFin()
        );
        double prixTotal = vehicle.getPrixParJour() * nombreJours;
        location.setPrixTotal(prixTotal);

        vehicle.setStatut(VehiculeStatus.LOUE);
        vehiculeRepository.save(vehicle);

        Location savedLocation = locationRepository.save(location);

        return vehiculeMapper.toLocationDTO(savedLocation);
    }

    @Override
    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location non trouvée"));

        Vehicule vehicle = location.getVehicule();
        vehicle.setStatut(VehiculeStatus.DISPONIBLE);
        vehiculeRepository.save(vehicle);

        locationRepository.deleteById(id);
    }

    @Override
    public Double calculerPrixTotal(Long vehiculeId, int nombreJours) {
        Vehicule vehicle = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        return vehicle.getPrixParJour() * nombreJours;
    }
}