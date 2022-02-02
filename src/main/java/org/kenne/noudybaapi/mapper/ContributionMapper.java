package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Contribution;
import org.kenne.noudybaapi.dto.ContributionRequestDTO;
import org.kenne.noudybaapi.dto.ContributionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContributionMapper {

    ContributionMapper INSTANCE = Mappers.getMapper(ContributionMapper.class);

    ContributionResponseDTO fromEntityToResponse(Contribution contribution);

    Contribution fromRequestToEntity(ContributionRequestDTO requestDTO);
}
