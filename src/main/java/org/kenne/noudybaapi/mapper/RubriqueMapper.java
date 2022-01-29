package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Rubrique;
import org.kenne.noudybaapi.dto.RubriqueRequestDTO;
import org.kenne.noudybaapi.dto.RubriqueResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.persistence.MappedSuperclass;

@Mapper(componentModel = "spring")
public interface RubriqueMapper {
    public RubriqueMapper INSTANCE = Mappers.getMapper(RubriqueMapper.class);

    public RubriqueResponseDTO fromEntityToResponse(Rubrique rubrique);

    public Rubrique fromRequestToEntity(RubriqueRequestDTO requestDTO);
}
