package riyad.houda.gestionLocationVehicules.exceptions;

public class VehiculeNotFoundException extends RuntimeException {

    public VehiculeNotFoundException(Long id) {
        super("Aucun véhicule trouvé avec l'ID : " + id);
    }

    public VehiculeNotFoundException(String matricule) {
        super("Aucun véhicule trouvé avec le matricule : " + matricule);
    }
}