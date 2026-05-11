package riyad.houda.gestionLocationVehicules.repositories;

import riyad.houda.gestionLocationVehicules.entities.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgenceRepository extends JpaRepository<Agence, Long> {

    List<Agence> findByVille(String ville);
}
