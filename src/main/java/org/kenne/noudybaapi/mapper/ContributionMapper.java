package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Contribution;
import org.kenne.noudybaapi.domain.Souscription;
import org.kenne.noudybaapi.dto.ContributionRequestDTO;
import org.kenne.noudybaapi.dto.ContributionResponseDTO;
import org.kenne.noudybaapi.dto.SouscriptionRequestDTO;
import org.kenne.noudybaapi.dto.SouscriptionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContributionMapper {

    public ContributionMapper INSTANCE = Mappers.getMapper(ContributionMapper.class);

    ContributionResponseDTO fromEntityToResponse(Contribution contribution);

    Contribution fromRequestToEntity(ContributionRequestDTO requestDTO);
}
