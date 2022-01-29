package org.kenne.noudybaapi.service.imp;

import lombok.AllArgsConstructor;
import org.kenne.noudybaapi.domain.Operation;
import org.kenne.noudybaapi.dto.OperationRequestDTO;
import org.kenne.noudybaapi.mapper.OperationMapper;
import org.kenne.noudybaapi.repository.AnneeRepository;
import org.kenne.noudybaapi.repository.MembreRepository;
import org.kenne.noudybaapi.repository.OperationRepository;
import org.kenne.noudybaapi.repository.RubriqueRepository;
import org.kenne.noudybaapi.service.declaration.OperationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@AllArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final AnneeRepository anneeRepository;
    private final MembreRepository membreRepository;
    private final RubriqueRepository rubriqueRepository;

    @Override
    public Operation save(OperationRequestDTO requestDTO) {
        Operation operation = OperationMapper.INSTANCE.fromRequestToEntity(requestDTO);
        operation.setAnnee(anneeRepository.getById(requestDTO.getIdAnnee()));
        operation.setMembre(membreRepository.getById(requestDTO.getIdMembre()));
        operation.setRubrique(rubriqueRepository.getById(requestDTO.getIdRubrique()));
        operation.setHeure(Date.from(Instant.now()));
        return operationRepository.save(operation);
    }

    @Override
    public Operation edit(OperationRequestDTO requestDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {
        operationRepository.deleteById(id);
    }
}
