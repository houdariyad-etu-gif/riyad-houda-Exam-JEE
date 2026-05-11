package riyad.houda.gestionLocationVehicules.services;

import riyad.houda.gestionLocationVehicules.dtos.AgenceDTO;
import java.util.List;

public interface AgenceService {
    List<AgenceDTO> getAllAgences();
    AgenceDTO getAgenceById(Long id);
    List<AgenceDTO> getAgencesByVille(String ville);
}