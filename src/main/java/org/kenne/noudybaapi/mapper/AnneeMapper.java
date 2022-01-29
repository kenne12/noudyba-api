package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Annee;
import org.kenne.noudybaapi.dto.AnneeRequestDTO;
import org.kenne.noudybaapi.dto.AnneeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnneeMapper {

    public AnneeMapper INSTANCE = Mappers.getMapper(AnneeMapper.class);

    AnneeResponseDTO fromEntityToResponse(Annee annee);

    Annee fromRequestToEntity(AnneeRequestDTO requestDTO);
}
