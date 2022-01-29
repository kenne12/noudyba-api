package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Membre;
import org.kenne.noudybaapi.dto.MembreRequestDTO;
import org.kenne.noudybaapi.dto.MembreResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MembreMapper {

    public MembreMapper INSTANCE = Mappers.getMapper(MembreMapper.class);

    MembreResponseDTO fromEntityToResponse(Membre membre);

    Membre fromRequestToEntity(MembreRequestDTO requestDTO);
}
