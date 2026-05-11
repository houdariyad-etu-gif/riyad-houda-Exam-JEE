package riyad.houda.gestionLocationVehicules.services;

import riyad.houda.gestionLocationVehicules.dtos.LocationDTO;
import riyad.houda.gestionLocationVehicules.entities.Location;
import riyad.houda.gestionLocationVehicules.entities.Vehicule;
import riyad.houda.gestionLocationVehicules.enums.VehiculeStatus;
import riyad.houda.gestionLocationVehicules.exceptions.*;
import riyad.houda.gestionLocationVehicules.mappers.VehiculeMapper;
import riyad.houda.gestionLocationVehicules.repositories.LocationRepository;
import riyad.houda.gestionLocationVehicules.repositories.VehiculeRepository;
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
                .orElseThrow(() -> new LocationNotFoundException(id));
        return vehiculeMapper.toLocationDTO(location);
    }

    @Override
    public List<LocationDTO> getLocationsByVehicule(Long vehiculeId) {
        vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new VehiculeNotFoundException(vehiculeId));

        return locationRepository.findByVehiculeId(vehiculeId).stream()
                .map(vehiculeMapper::toLocationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LocationDTO createLocation(LocationDTO locationDTO) {
        Vehicule vehicle = vehiculeRepository.findById(locationDTO.getVehiculeId())
                .orElseThrow(() -> new VehiculeNotFoundException(locationDTO.getVehiculeId()));

        if (vehicle.getStatut() != VehiculeStatus.DISPONIBLE) {
            throw new VehiculeIndisponibleException(vehicle.getId(), vehicle.getStatut().toString());
        }

        if (locationDTO.getDateDebut() == null || locationDTO.getDateFin() == null) {
            throw new DateLocationInvalideException("Les dates de début et de fin sont obligatoires");
        }

        if (locationDTO.getDateFin().isBefore(locationDTO.getDateDebut())) {
            throw new DateLocationInvalideException(locationDTO.getDateDebut(), locationDTO.getDateFin());
        }

        if (locationDTO.getDateDebut().isBefore(LocalDateTime.now())) {
            throw new DateLocationInvalideException("La date de début ne peut pas être dans le passé");
        }

        long nombreJours = ChronoUnit.DAYS.between(
                locationDTO.getDateDebut(),
                locationDTO.getDateFin()
        );

        if (nombreJours <= 0) {
            throw new DateLocationInvalideException("La location doit durer au moins 1 jour");
        }

        if (nombreJours > 30) {
            throw new DureeLocationDepasseeException(30);
        }

        Location location = new Location();
        location.setDateDebut(locationDTO.getDateDebut());
        location.setDateFin(locationDTO.getDateFin());
        location.setVehicule(vehicle);

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
                .orElseThrow(() -> new LocationNotFoundException(id));

        Vehicule vehicle = location.getVehicule();

        vehicle.setStatut(VehiculeStatus.DISPONIBLE);
        vehiculeRepository.save(vehicle);

        locationRepository.deleteById(id);
    }

    @Override
    public Double calculerPrixTotal(Long vehiculeId, int nombreJours) {

        Vehicule vehicle = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new VehiculeNotFoundException(vehiculeId));

        if (nombreJours <= 0) {
            throw new DateLocationInvalideException("Le nombre de jours doit être supérieur à 0");
        }

        if (nombreJours > 30) {
            throw new DureeLocationDepasseeException(30);
        }

        return vehicle.getPrixParJour() * nombreJours;
    }
}