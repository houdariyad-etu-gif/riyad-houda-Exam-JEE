package riyad.houda.gestionLocationVehicules.exceptions;

public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException(Long id) {
        super("Aucune location trouvée avec l'ID : " + id);
    }
}