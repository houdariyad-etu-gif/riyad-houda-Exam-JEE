package riyad.houda.gestionLocationVehicules.entities;

import jakarta.persistence.*;
import lombok.*;
import riyad.houda.gestionLocationVehicules.enums.FuelType;
import riyad.houda.gestionLocationVehicules.enums.TransmissionType;

@Entity
@DiscriminatorValue("VOITURE")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Voiture extends Vehicule {

    private Integer nombrePortes;

    @Enumerated(EnumType.STRING)
    private FuelType typeCarburant;

    @Enumerated(EnumType.STRING)
    private TransmissionType boiteVitesse;
}