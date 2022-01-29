package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.dto.PayementSouscriptionRequestDTO;
import org.kenne.noudybaapi.dto.PayementSouscriptionResponseDTO;

import java.util.List;

public interface PayementSouscriptionService {

    PayementSouscriptionResponseDTO save(PayementSouscriptionRequestDTO requestDTO);

    PayementSouscriptionResponseDTO edit(PayementSouscriptionRequestDTO requestDTO);

    boolean delete(Long id);

    PayementSouscriptionResponseDTO findById(Long id);

    List<PayementSouscriptionResponseDTO> getAll();
}
