package riyad.houda.gestionLocationVehicules.exceptions;

import java.time.LocalDateTime;

public class DateLocationInvalideException extends RuntimeException {

    public DateLocationInvalideException() {
        super("La date de fin doit être postérieure à la date de début");
    }

    public DateLocationInvalideException(LocalDateTime dateDebut, LocalDateTime dateFin) {
        super("Date invalide : début (" + dateDebut + ") doit être avant fin (" + dateFin + ")");
    }

    public DateLocationInvalideException(String message) {
        super(message);
    }
}