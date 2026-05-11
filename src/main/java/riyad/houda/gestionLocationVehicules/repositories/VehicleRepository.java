package riyad.houda.gestionLocationVehicules.repositories;

import riyad.houda.gestionLocationVehicules.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import riyad.houda.gestionLocationVehicules.enums.VehiculeStatus;


import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicule, Long> {

    List<Vehicule> findByStatut(VehiculeStatus statut);

    List<Vehicule> findByAgenceId(Long agenceId);

    List<Vehicule> findByMarqueContainingIgnoreCase(String marque);
}