package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.dto.EvenementRequestDTO;
import org.kenne.noudybaapi.dto.EvenementResponseDTO;

import java.util.List;

public interface EvenementService {

    EvenementResponseDTO save(EvenementRequestDTO requestDTO);

    EvenementResponseDTO edit(EvenementRequestDTO requestDTO);

    boolean delete(Long id);

    List<EvenementResponseDTO> getAllEvents();

    List<EvenementResponseDTO> getAllByIdanne(int idAnnee);

    EvenementResponseDTO findById(Long id);
}
