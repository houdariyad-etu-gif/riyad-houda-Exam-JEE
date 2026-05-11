package riyad.houda.gestionLocationVehicules.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VehiculeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleVehiculeNotFound(VehiculeNotFoundException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "VEHICULE_NOT_FOUND", ex.getMessage(), request);
    }

    @ExceptionHandler(AgenceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAgenceNotFound(AgenceNotFoundException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "AGENCE_NOT_FOUND", ex.getMessage(), request);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleLocationNotFound(LocationNotFoundException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "LOCATION_NOT_FOUND", ex.getMessage(), request);
    }

    @ExceptionHandler(VehiculeIndisponibleException.class)
    public ResponseEntity<Map<String, Object>> handleVehiculeIndisponible(VehiculeIndisponibleException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "VEHICULE_INDISPONIBLE", ex.getMessage(), request);
    }

    @ExceptionHandler(DateLocationInvalideException.class)
    public ResponseEntity<Map<String, Object>> handleDateInvalide(DateLocationInvalideException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "DATE_INVALIDE", ex.getMessage(), request);
    }

    @ExceptionHandler(PermisInvalideException.class)
    public ResponseEntity<Map<String, Object>> handlePermisInvalide(PermisInvalideException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "PERMIS_INVALIDE", ex.getMessage(), request);
    }

    @ExceptionHandler(AgeMinimumException.class)
    public ResponseEntity<Map<String, Object>> handleAgeMinimum(AgeMinimumException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "AGE_MINIMUM_NON_ATTEINT", ex.getMessage(), request);
    }

    @ExceptionHandler(DureeLocationDepasseeException.class)
    public ResponseEntity<Map<String, Object>> handleDureeDepassee(DureeLocationDepasseeException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "DUREE_LOCATION_DEPASSEE", ex.getMessage(), request);
    }

    @ExceptionHandler(UtilisateurNonAutoriseException.class)
    public ResponseEntity<Map<String, Object>> handleNonAutorise(UtilisateurNonAutoriseException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.FORBIDDEN, "UTILISATEUR_NON_AUTORISE", ex.getMessage(), request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        return buildErrorResponse(HttpStatus.FORBIDDEN, "ACCESS_DENIED", "Vous n'avez pas les droits nécessaires", request);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(
            HttpStatus status, String code, String message, WebRequest request) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("code", code);
        response.put("message", message);
        response.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(response, status);
    }
}