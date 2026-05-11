package riyad.houda.gestionLocationVehicules.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Double prixTotal;


    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;
}