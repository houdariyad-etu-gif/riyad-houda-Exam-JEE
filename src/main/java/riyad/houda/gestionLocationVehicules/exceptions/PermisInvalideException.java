package riyad.houda.gestionLocationVehicules.exceptions;

public class PermisInvalideException extends RuntimeException {

    public PermisInvalideException() {
        super("Vous devez avoir un permis valide pour louer un véhicule");
    }

    public PermisInvalideException(String typeVehicule) {
        super("Permis invalide pour louer ce type de véhicule : " + typeVehicule);
    }
}