package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Ville;
import org.kenne.noudybaapi.dto.VilleRequestDTO;
import org.kenne.noudybaapi.dto.VilleResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VilleMapper {
    public VilleMapper INSTANCE = Mappers.getMapper(VilleMapper.class);

    public VilleResponseDTO fromEntityToResponse(Ville ville);

    public Ville fromRequestToEntity(VilleRequestDTO requestDTO);
}
