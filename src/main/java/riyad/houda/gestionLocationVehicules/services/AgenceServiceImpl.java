package riyad.houda.gestionLocationVehicules.services;

import riyad.houda.gestionLocationVehicules.dtos.AgenceDTO;
import riyad.houda.gestionLocationVehicules.mappers.VehiculeMapper;
import riyad.houda.gestionLocationVehicules.repositories.AgenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements AgenceService {

    private final AgenceRepository agenceRepository;
    private final VehiculeMapper vehiculeMapper;

    @Override
    public List<AgenceDTO> getAllAgences() {
        return agenceRepository.findAll().stream()
                .map(vehiculeMapper::toAgenceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AgenceDTO getAgenceById(Long id) {
        return agenceRepository.findById(id)
                .map(vehiculeMapper::toAgenceDTO)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée"));
    }

    @Override
    public List<AgenceDTO> getAgencesByVille(String ville) {
        return agenceRepository.findByVille(ville).stream()
                .map(vehiculeMapper::toAgenceDTO)
                .collect(Collectors.toList());
    }
}