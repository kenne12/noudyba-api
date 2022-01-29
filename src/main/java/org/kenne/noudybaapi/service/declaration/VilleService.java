package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.dto.VilleRequestDTO;
import org.kenne.noudybaapi.dto.VilleResponseDTO;

import java.util.List;

public interface VilleService {

    List<VilleResponseDTO> findAll();

    VilleResponseDTO findById(Integer id);

    VilleResponseDTO save(VilleRequestDTO requestDTO);

    VilleResponseDTO edit(VilleRequestDTO requestDTO);

    boolean delete(Integer id);
}
