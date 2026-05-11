package riyad.houda.gestionLocationVehicules.exceptions;

public class AgeMinimumException extends RuntimeException {

    public AgeMinimumException(int ageMinimum) {
        super("Vous devez avoir au moins " + ageMinimum + " ans pour louer ce véhicule");
    }

    public AgeMinimumException(String typeVehicule, int ageMinimum) {
        super("Pour louer une " + typeVehicule + ", l'âge minimum est de " + ageMinimum + " ans");
    }
}