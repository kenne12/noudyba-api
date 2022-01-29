package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.domain.Operation;
import org.kenne.noudybaapi.dto.OperationRequestDTO;

public interface OperationService {

    Operation save(OperationRequestDTO requestDTO);

    Operation edit(OperationRequestDTO requestDTO);

    void delete(Long id);
}
