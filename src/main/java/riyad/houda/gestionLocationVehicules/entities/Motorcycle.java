package riyad.houda.gestionLocationVehicules.entities;

import jakarta.persistence.*;
import lombok.*;
import riyad.houda.gestionLocationVehicules.enums.MotorcycleType;

@Entity
@DiscriminatorValue("MOTO")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Motorcycle extends Vehicule {

    private Double cylindree;

    @Enumerated(EnumType.STRING)
    private MotorcycleType typeMoto;

    private Boolean casqueInclus;
}
