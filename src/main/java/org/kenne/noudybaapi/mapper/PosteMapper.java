package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Poste;
import org.kenne.noudybaapi.dto.PosteRequestDTO;
import org.kenne.noudybaapi.dto.PosteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PosteMapper {
    public PosteMapper INSTANCE = Mappers.getMapper(PosteMapper.class);

    public PosteResponseDTO fromEntityToResponse(Poste poste);

    public Poste fromRequestToEntity(PosteRequestDTO requestDTO);

    public PosteRequestDTO fromEntityToRequest(Poste poste);
}
