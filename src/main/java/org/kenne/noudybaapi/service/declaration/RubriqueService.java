package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.dto.RubriqueRequestDTO;
import org.kenne.noudybaapi.dto.RubriqueResponseDTO;

import java.util.List;

public interface RubriqueService {

    RubriqueResponseDTO save(RubriqueRequestDTO requestDTO);

    RubriqueResponseDTO edit(RubriqueRequestDTO requestDTO);

    boolean delete(Integer id);

    RubriqueResponseDTO findById(Integer id);

    List<RubriqueResponseDTO> findAll();
}
