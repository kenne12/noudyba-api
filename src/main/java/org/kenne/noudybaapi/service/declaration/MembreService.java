package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.dto.MembreRequestDTO;
import org.kenne.noudybaapi.dto.MembreResponseDTO;

import java.util.List;

public interface MembreService {

    MembreResponseDTO save(MembreRequestDTO requestDTO) throws Exception;

    MembreResponseDTO edit(MembreRequestDTO requestDTO) throws Exception;

    MembreResponseDTO findById(Long id);

    boolean delete(Long id);

    List<MembreResponseDTO> getAllMembers();

    void uplaoadImage(Long id);
}
