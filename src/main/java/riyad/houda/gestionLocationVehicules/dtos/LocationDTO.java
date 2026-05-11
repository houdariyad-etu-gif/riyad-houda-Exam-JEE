package riyad.houda.gestionLocationVehicules.dtos;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class LocationDTO {
    private Long id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Double prixTotal;
    private Long vehiculeId;
    private String vehiculeInfo;
}