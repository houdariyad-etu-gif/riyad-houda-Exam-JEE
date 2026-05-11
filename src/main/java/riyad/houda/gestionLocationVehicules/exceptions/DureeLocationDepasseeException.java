package riyad.houda.gestionLocationVehicules.exceptions;

public class DureeLocationDepasseeException extends RuntimeException {

    public DureeLocationDepasseeException(int dureeMax) {
        super("La durée de location ne peut pas dépasser " + dureeMax + " jours");
    }
}