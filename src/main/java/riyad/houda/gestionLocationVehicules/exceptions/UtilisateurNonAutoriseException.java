package riyad.houda.gestionLocationVehicules.exceptions;

public class UtilisateurNonAutoriseException extends RuntimeException {

    public UtilisateurNonAutoriseException(String username, String action) {
        super("L'utilisateur '" + username + "' n'est pas autorisé à effectuer l'action : " + action);
    }

    public UtilisateurNonAutoriseException(String roleRequis) {
        super("Action réservée aux utilisateurs avec le rôle : " + roleRequis);
    }
}