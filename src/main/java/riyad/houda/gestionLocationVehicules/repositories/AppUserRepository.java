package riyad.houda.gestionLocationVehicules.repositories;

import riyad.houda.gestionLocationVehicules.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}
