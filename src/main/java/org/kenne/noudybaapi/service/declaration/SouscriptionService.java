package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.dto.SouscriptionRequestDTO;
import org.kenne.noudybaapi.dto.SouscriptionResponseDTO;

import java.util.List;

public interface SouscriptionService {

    SouscriptionResponseDTO save(SouscriptionRequestDTO requestDTO);

    SouscriptionResponseDTO edit(SouscriptionRequestDTO requestDTO);

    boolean delete(Long id);

    List<SouscriptionResponseDTO> getAllSouscription();

    List<SouscriptionResponseDTO> getAllByIdanne(int idAnnee);

    SouscriptionResponseDTO findById(Long id);
}
