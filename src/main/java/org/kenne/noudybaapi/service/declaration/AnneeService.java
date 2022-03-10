package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.dto.AnneeRequestDTO;
import org.kenne.noudybaapi.dto.AnneeResponseDTO;

import java.util.List;

public interface AnneeService {

    AnneeResponseDTO save(AnneeRequestDTO requestDTO);

    AnneeResponseDTO edit(AnneeRequestDTO requestDTO);

    boolean delete(Integer id);

    List<AnneeResponseDTO> findAll();

    List<AnneeResponseDTO> findAll(boolean etat);

    AnneeResponseDTO findById(Integer id);
}
