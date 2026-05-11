package riyad.houda.gestionLocationVehicules.dtos;

import riyad.houda.gestionLocationVehicules.enums.MotorcycleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MotorcycleDTO extends VehiculeDTO {
    private Double cylindree;
    private MotorcycleType typeMoto;
    private Boolean casqueInclus;
}