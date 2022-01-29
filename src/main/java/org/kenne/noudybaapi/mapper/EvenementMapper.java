package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Annee;
import org.kenne.noudybaapi.domain.Evenement;
import org.kenne.noudybaapi.dto.AnneeRequestDTO;
import org.kenne.noudybaapi.dto.AnneeResponseDTO;
import org.kenne.noudybaapi.dto.EvenementRequestDTO;
import org.kenne.noudybaapi.dto.EvenementResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EvenementMapper {

    public EvenementMapper INSTANCE = Mappers.getMapper(EvenementMapper.class);

    EvenementResponseDTO fromEntityToResponse(Evenement evenement);

    Evenement fromRequestToEntity(EvenementRequestDTO requestDTO);
}
