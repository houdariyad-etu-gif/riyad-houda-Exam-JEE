package riyad.houda.gestionLocationVehicules.dtos;

import lombok.Data;
import riyad.houda.gestionLocationVehicules.enums.VehiculeStatus;

import java.time.LocalDate;

@Data
public class VehiculeDTO {
    private Long id;
    private String marque;
    private String modele;
    private String matricule;
    private Double prixParJour;
    private LocalDate dateMiseEnService;
    private VehiculeStatus statut;
    private Long agenceId;
    private String agenceNom;
    private String typeVehicule;
}