package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Membre;
import org.kenne.noudybaapi.domain.Operation;
import org.kenne.noudybaapi.dto.MembreRequestDTO;
import org.kenne.noudybaapi.dto.MembreResponseDTO;
import org.kenne.noudybaapi.dto.OperationRequestDTO;
import org.kenne.noudybaapi.dto.OperationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    public OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    OperationResponseDTO fromEntityToResponse(Operation operation);

    Operation fromRequestToEntity(OperationRequestDTO requestDTO);
}
