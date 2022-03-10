package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.PayementSouscription;
import org.kenne.noudybaapi.dto.PayementSouscriptionRequestDTO;
import org.kenne.noudybaapi.dto.PayementSouscriptionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PayementSouscriptionMapper {

    PayementSouscriptionMapper INSTANCE = Mappers.getMapper(PayementSouscriptionMapper.class);

    PayementSouscriptionResponseDTO fromEntityToResponse(PayementSouscription payementSouscription);

    PayementSouscription fromRequestToEntity(PayementSouscriptionRequestDTO requestDTO);
}
