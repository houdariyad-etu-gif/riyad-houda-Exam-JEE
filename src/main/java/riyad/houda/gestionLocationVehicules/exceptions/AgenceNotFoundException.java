package riyad.houda.gestionLocationVehicules.exceptions;

public class AgenceNotFoundException extends RuntimeException {

    public AgenceNotFoundException(Long id) {
        super("Aucune agence trouvée avec l'ID : " + id);
    }

    public AgenceNotFoundException(String nom) {
        super("Aucune agence trouvée avec le nom : " + nom);
    }

    public AgenceNotFoundException(String ville, boolean parVille) {
        super("Aucune agence trouvée dans la ville : " + ville);
    }
}