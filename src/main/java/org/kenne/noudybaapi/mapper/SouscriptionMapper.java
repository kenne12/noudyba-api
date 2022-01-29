package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Evenement;
import org.kenne.noudybaapi.domain.Souscription;
import org.kenne.noudybaapi.dto.EvenementRequestDTO;
import org.kenne.noudybaapi.dto.EvenementResponseDTO;
import org.kenne.noudybaapi.dto.SouscriptionRequestDTO;
import org.kenne.noudybaapi.dto.SouscriptionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SouscriptionMapper {

    public SouscriptionMapper INSTANCE = Mappers.getMapper(SouscriptionMapper.class);

    SouscriptionResponseDTO fromEntityToResponse(Souscription souscription);

    Souscription fromRequestToEntity(SouscriptionRequestDTO requestDTO);

    SouscriptionRequestDTO fromEntityToRequest(Souscription souscription);
}
