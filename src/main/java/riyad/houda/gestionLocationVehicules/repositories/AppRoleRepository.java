package riyad.houda.gestionLocationVehicules.repositories;

import riyad.houda.gestionLocationVehicules.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    Optional<AppRole> findByRoleName(String roleName);
}