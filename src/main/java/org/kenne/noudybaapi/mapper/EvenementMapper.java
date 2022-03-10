package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Evenement;
import org.kenne.noudybaapi.dto.EvenementRequestDTO;
import org.kenne.noudybaapi.dto.EvenementResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EvenementMapper {

    EvenementMapper INSTANCE = Mappers.getMapper(EvenementMapper.class);

    EvenementResponseDTO fromEntityToResponse(Evenement evenement);

    Evenement fromRequestToEntity(EvenementRequestDTO requestDTO);
}
