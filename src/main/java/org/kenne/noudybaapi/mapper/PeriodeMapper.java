package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Periode;
import org.kenne.noudybaapi.dto.PeriodeRequestDTO;
import org.kenne.noudybaapi.dto.PeriodeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PeriodeMapper {


    PeriodeMapper INSTANCE = Mappers.getMapper(PeriodeMapper.class);

    PeriodeResponse fromEntityToResponse(Periode periode);

    Periode fromRequestToEntity(PeriodeRequestDTO request);
}
