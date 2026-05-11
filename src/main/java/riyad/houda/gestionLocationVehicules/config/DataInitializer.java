package riyad.houda.gestionLocationVehicules.config;


import riyad.houda.gestionLocationVehicules.entities.*;
import riyad.houda.gestionLocationVehicules.enums.FuelType;
import riyad.houda.gestionLocationVehicules.enums.MotorcycleType;
import riyad.houda.gestionLocationVehicules.enums.TransmissionType;
import riyad.houda.gestionLocationVehicules.enums.VehiculeStatus;
import riyad.houda.gestionLocationVehicules.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import riyad.houda.gestionLocationVehicules.entities.Location;
import riyad.houda.gestionLocationVehicules.repositories.AgenceRepository;
import riyad.houda.gestionLocationVehicules.repositories.AppRoleRepository;
import riyad.houda.gestionLocationVehicules.repositories.AppUserRepository;
import riyad.houda.gestionLocationVehicules.repositories.LocationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;
    private final LocationRepository locationRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("========================================");
        System.out.println(" INITIALISATION DES DONNÉES");
        System.out.println("========================================");

        // 1. RÔLES
        AppRole roleAdmin = new AppRole(); roleAdmin.setRoleName("ROLE_ADMIN");
        AppRole roleEmploye = new AppRole(); roleEmploye.setRoleName("ROLE_EMPLOYE");
        AppRole roleClient = new AppRole(); roleClient.setRoleName("ROLE_CLIENT");
        appRoleRepository.saveAll(Arrays.asList(roleAdmin, roleEmploye, roleClient));
        System.out.println(" Rôles créés");

        // 2. UTILISATEURS
        AppUser admin = new AppUser(); admin.setUsername("admin"); admin.setPassword(passwordEncoder.encode("admin123")); admin.setEmail("admin@test.com"); admin.setRoles(Arrays.asList(roleAdmin));
        AppUser employe = new AppUser(); employe.setUsername("employe"); employe.setPassword(passwordEncoder.encode("employe123")); employe.setEmail("employe@test.com"); employe.setRoles(Arrays.asList(roleEmploye));
        AppUser client = new AppUser(); client.setUsername("client"); client.setPassword(passwordEncoder.encode("client123")); client.setEmail("client@test.com"); client.setRoles(Arrays.asList(roleClient));
        appUserRepository.saveAll(Arrays.asList(admin, employe, client));
        System.out.println(" Utilisateurs créés");

        // 3. AGENCES
        Agence agence1 = new Agence(); agence1.setNom("Location Casablanca"); agence1.setAdresse("123 Bd Mohammed V"); agence1.setVille("Casablanca"); agence1.setTelephone("0522123456");
        Agence agence2 = new Agence(); agence2.setNom("Location Rabat"); agence2.setAdresse("45 Ave Hassan II"); agence2.setVille("Rabat"); agence2.setTelephone("0537789012");
        agenceRepository.saveAll(Arrays.asList(agence1, agence2));
        System.out.println(" Agences créées");

        // 4. VÉHICULES - LIGNE CORRIGÉE
        Voiture car1 = new Voiture();
        car1.setMarque("Dacia");
        car1.setModele("Logan");
        car1.setMatricule("123-A-45");
        car1.setPrixParJour(250.0);
        car1.setDateMiseEnService(LocalDate.of(2022,1,1));
        car1.setStatut(VehiculeStatus.DISPONIBLE);
        car1.setAgence(agence1);
        car1.setNombrePortes(5);
        car1.setTypeCarburant(FuelType.ESSENCE);
        car1.setBoiteVitesse(TransmissionType.MANUELLE);

        Motorcycle moto1 = new Motorcycle();
        moto1.setMarque("Yamaha");
        moto1.setModele("MT-07");
        moto1.setMatricule("678-B-90");
        moto1.setPrixParJour(400.0);
        moto1.setDateMiseEnService(LocalDate.of(2023,6,15));
        moto1.setStatut(VehiculeStatus.DISPONIBLE);
        moto1.setAgence(agence2);
        moto1.setCylindree(689.0);
        moto1.setTypeMoto(MotorcycleType.ROADSTER);
        moto1.setCasqueInclus(true);

        vehiculeRepository.saveAll(Arrays.asList(car1, moto1));
        System.out.println(" Véhicules créés");

        // 5. LOCATIONS
        Location loc1 = new Location();
        loc1.setDateDebut(LocalDateTime.now().minusDays(5));
        loc1.setDateFin(LocalDateTime.now().minusDays(2));
        loc1.setPrixTotal(750.0);
        loc1.setVehicule(car1);
        locationRepository.save(loc1);
        System.out.println(" Location créée");

        System.out.println("========================================");
        System.out.println(" IDENTIFIANTS DE TEST :");
        System.out.println("   ADMIN   → admin / admin123");
        System.out.println("   EMPLOYE → employe / employe123");
        System.out.println("   CLIENT  → client / client123");
        System.out.println("========================================");
    }
}