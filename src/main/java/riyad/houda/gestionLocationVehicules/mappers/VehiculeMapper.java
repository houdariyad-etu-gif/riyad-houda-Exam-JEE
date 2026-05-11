package riyad.houda.gestionLocationVehicules.mappers;

import riyad.houda.gestionLocationVehicules.dtos.*;
import riyad.houda.gestionLocationVehicules.entities.*;
import org.springframework.stereotype.Component;
import riyad.houda.gestionLocationVehicules.dtos.LocationDTO;
import riyad.houda.gestionLocationVehicules.dtos.VehiculeDTO;

@Component
public class VehiculeMapper {

    public VehiculeDTO toDTO(Vehicule vehicule) {
        if (vehicule == null) return null;

        if (vehicule instanceof Voiture) {
            VoitureDTO dto = new VoitureDTO();
            mapCommonFields(vehicule, dto);
            Voiture car = (Voiture) vehicule;
            dto.setNombrePortes(car.getNombrePortes());
            dto.setTypeCarburant(car.getTypeCarburant());
            dto.setBoiteVitesse(car.getBoiteVitesse());
            dto.setTypeVehicule("VOITURE");
            return dto;
        }
        else if (vehicule instanceof Motorcycle) {
            MotorcycleDTO dto = new MotorcycleDTO();
            mapCommonFields(vehicule, dto);
            Motorcycle moto = (Motorcycle) vehicule;
            dto.setCylindree(moto.getCylindree());
            dto.setTypeMoto(moto.getTypeMoto());
            dto.setCasqueInclus(moto.getCasqueInclus());
            dto.setTypeVehicule("MOTO");
            return dto;
        }
        return null;
    }

    private void mapCommonFields(Vehicule vehicle, VehiculeDTO dto) {
        dto.setId(vehicle.getId());
        dto.setMarque(vehicle.getMarque());
        dto.setModele(vehicle.getModele());
        dto.setMatricule(vehicle.getMatricule());
        dto.setPrixParJour(vehicle.getPrixParJour());
        dto.setDateMiseEnService(vehicle.getDateMiseEnService());
        dto.setStatut(vehicle.getStatut());
        if (vehicle.getAgence() != null) {
            dto.setAgenceId(vehicle.getAgence().getId());
            dto.setAgenceNom(vehicle.getAgence().getNom());
        }
    }


    public Vehicule toEntity(VehiculeDTO dto) {
        return null;
    }

    public AgenceDTO toAgenceDTO(Agence agence) {
        if (agence == null) return null;
        AgenceDTO dto = new AgenceDTO();
        dto.setId(agence.getId());
        dto.setNom(agence.getNom());
        dto.setAdresse(agence.getAdresse());
        dto.setVille(agence.getVille());
        dto.setTelephone(agence.getTelephone());
        return dto;
    }

    public LocationDTO toLocationDTO(Location location) {
        if (location == null) return null;
        LocationDTO dto = new LocationDTO();
        dto.setId(location.getId());
        dto.setDateDebut(location.getDateDebut());
        dto.setDateFin(location.getDateFin());
        dto.setPrixTotal(location.getPrixTotal());
        if (location.getVehicule() != null) {
            dto.setVehiculeId(location.getVehicule().getId());
            dto.setVehiculeInfo(location.getVehicule().getMarque() + " " +
                    location.getVehicule().getModele());
        }
        return dto;
    }
}