package org.kenne.noudybaapi.mapper;

import org.kenne.noudybaapi.domain.Utilisateur;
import org.kenne.noudybaapi.dto.UtilisateurRequestDTO;
import org.kenne.noudybaapi.dto.UtilisateurResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    public UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    public UtilisateurResponseDTO fromEntityToResponse(Utilisateur utilisateur);

    public Utilisateur fromRequestToEntity(UtilisateurRequestDTO requestDTO);

}
