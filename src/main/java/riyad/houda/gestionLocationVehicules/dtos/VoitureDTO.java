package riyad.houda.gestionLocationVehicules.dtos;

import riyad.houda.gestionLocationVehicules.enums.FuelType;
import riyad.houda.gestionLocationVehicules.enums.TransmissionType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VoitureDTO extends VehiculeDTO {
    private Integer nombrePortes;
    private FuelType typeCarburant;
    private TransmissionType boiteVitesse;
}