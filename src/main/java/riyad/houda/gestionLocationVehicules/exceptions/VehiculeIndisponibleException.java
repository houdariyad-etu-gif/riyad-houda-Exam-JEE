package riyad.houda.gestionLocationVehicules.exceptions;

public class VehiculeIndisponibleException extends RuntimeException {

    public VehiculeIndisponibleException(String matricule) {
        super("Le véhicule avec matricule " + matricule + " n'est pas disponible pour location");
    }

    public VehiculeIndisponibleException(Long id, String statut) {
        super("Le véhicule ID " + id + " est actuellement " + statut + " et ne peut pas être loué");
    }
}