package org.kenne.noudybaapi.service.imp;

import lombok.AllArgsConstructor;
import org.kenne.noudybaapi.domain.Contribution;
import org.kenne.noudybaapi.domain.Evenement;
import org.kenne.noudybaapi.domain.Operation;
import org.kenne.noudybaapi.enumeration.OperationType;
import org.kenne.noudybaapi.dto.ContributionRequestDTO;
import org.kenne.noudybaapi.dto.ContributionResponseDTO;
import org.kenne.noudybaapi.dto.OperationRequestDTO;
import org.kenne.noudybaapi.exception.EntityNotFoundException;
import org.kenne.noudybaapi.mapper.ContributionMapper;
import org.kenne.noudybaapi.repository.ContributionRepository;
import org.kenne.noudybaapi.repository.EvenementRepository;
import org.kenne.noudybaapi.repository.MembreRepository;
import org.kenne.noudybaapi.service.declaration.ContributionService;
import org.kenne.noudybaapi.service.declaration.OperationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ContributionServiceImpl implements ContributionService {

    private final ContributionRepository contributionRepository;
    private final EvenementRepository evenementRepository;
    private final OperationService operationService;
    private final MembreRepository membreRepository;

    @Override
    public ContributionResponseDTO save(ContributionRequestDTO requestDTO) {

        Evenement evenement = evenementRepository.getById(requestDTO.getIdEvenement());

        OperationRequestDTO op = new OperationRequestDTO();
        op.setDateOperation(requestDTO.getDateContribution());
        op.setIdAnnee(evenement.getAnnee().getIdAnnee());
        op.setIdMembre(requestDTO.getIdMembre());
        op.setOperationType(OperationType.DON);
        op.setLibelle("Contribution ................... Dans la rubrique ......... " + evenement.getRubrique().getNom());
        op.setIdRubrique(evenement.getRubrique().getIdRubrique());
        op.setMontant(requestDTO.getMontant());
        Operation operation = operationService.save(op);

        Contribution contribution = ContributionMapper.INSTANCE.fromRequestToEntity(requestDTO);
        contribution.setEvenement(evenement);
        contribution.setMembre(membreRepository.getById(requestDTO.getIdMembre()));
        contribution.setOperation(operation);
        return ContributionMapper.INSTANCE.fromEntityToResponse(contributionRepository.save(contribution));
    }

    @Override
    public ContributionResponseDTO edit(ContributionRequestDTO requestDTO) {
        Contribution contribution = contributionRepository.findById(requestDTO.getIdContribution())
                .orElseThrow(() -> new EntityNotFoundException("Contribution not found with id : " + requestDTO.getIdContribution()));

        contribution.setMontant(requestDTO.getMontant());
        return ContributionMapper.INSTANCE.fromEntityToResponse(contributionRepository.save(contribution));
        //if(!Objects.equals(requestDTO.getMontant() , contribution.getMontant())
    }

    @Override
    public boolean delete(Long id) {
        Contribution contribution = contributionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contribution with id : " + id + " doesn't exist"));
        contributionRepository.deleteById(contribution.getIdContribution());
        operationService.delete(contribution.getOperation().getIdOperation());
        return true;
    }

    @Override
    public List<ContributionResponseDTO> getAll() {
        return contributionRepository.findAll()
                .stream()
                .map(ContributionMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ContributionResponseDTO findById(Long id) {
        Contribution contribution = contributionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contribution not found with id : " + id));
        return ContributionMapper.INSTANCE.fromEntityToResponse(contribution);
    }
}
