package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.dto.ContributionRequestDTO;
import org.kenne.noudybaapi.dto.ContributionResponseDTO;

import java.util.List;

public interface ContributionService {

    ContributionResponseDTO save(ContributionRequestDTO requestDTO);

    ContributionResponseDTO edit(ContributionRequestDTO requestDTO);

    boolean delete(Long id);

    List<ContributionResponseDTO> getAll();

    List<ContributionResponseDTO> getAllByIdannee(Integer idAnnee);

    ContributionResponseDTO findById(Long id);
}
