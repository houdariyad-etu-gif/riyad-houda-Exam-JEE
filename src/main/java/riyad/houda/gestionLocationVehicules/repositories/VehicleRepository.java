package riyad.houda.gestionLocationVehicules.repositories;

import riyad.houda.gestionLocationVehicules.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import riyad.houda.gestionLocationVehicules.enums.VehicleStatus;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByStatut(VehicleStatus statut);

    List<Vehicle> findByAgenceId(Long agenceId);

    List<Vehicle> findByMarqueContainingIgnoreCase(String marque);
}