package org.kenne.noudybaapi.service.imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.kenne.noudybaapi.domain.Annee;
import org.kenne.noudybaapi.domain.Evenement;
import org.kenne.noudybaapi.dto.EvenementRequestDTO;
import org.kenne.noudybaapi.dto.EvenementResponseDTO;
import org.kenne.noudybaapi.exception.EntityDeletionException;
import org.kenne.noudybaapi.exception.EntityNotFoundException;
import org.kenne.noudybaapi.mapper.EvenementMapper;
import org.kenne.noudybaapi.repository.*;
import org.kenne.noudybaapi.service.declaration.EvenementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class EvenementServiceImpl implements EvenementService {

    private final EvenementRepository evenementRepository;
    private final RubriqueRepository rubriqueRepository;
    private final AnneeRepository anneeRepository;
    private final ContributionRepository contributionRepository;
    private final SouscriptionRepository souscriptionRepository;

    @Override
    public EvenementResponseDTO save(EvenementRequestDTO requestDTO) {
        log.info("Save new event {}");
        Evenement evenement = EvenementMapper.INSTANCE.fromRequestToEntity(requestDTO);
        evenement.setCode(this.generateCode(4));
        evenement.setRubrique(rubriqueRepository.getById(requestDTO.getIdRubrique()));
        evenement.setAnnee(anneeRepository.getById(requestDTO.getIdAnnee()));
        evenement.setDateCreation(Date.from(Instant.now()));
        return EvenementMapper.INSTANCE.fromEntityToResponse(evenementRepository.save(evenement));
    }

    @Override
    public EvenementResponseDTO edit(EvenementRequestDTO requestDTO) {
        log.info("Edit event with {Id} ", requestDTO.getIdEvenement());
        Evenement evenement = evenementRepository.findById(requestDTO.getIdEvenement())
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id : " + requestDTO.getIdEvenement()));
        evenement.setDateDebut(requestDTO.getDateDebut());
        evenement.setDateFin(requestDTO.getDateFin());
        evenement.setAnnee(anneeRepository.getById(requestDTO.getIdAnnee()));
        evenement.setRubrique(rubriqueRepository.getById(requestDTO.getIdRubrique()));
        return EvenementMapper.INSTANCE.fromEntityToResponse(evenementRepository.save(evenement));
    }

    @Override
    public boolean delete(Long id) {
        log.info("Delete Event with id : ", id);
        if (!contributionRepository.findContributionByIdevenement(id).isEmpty())
            throw new EntityDeletionException("Event can not be deleted", "Event is associated to more contribution");

        if (!souscriptionRepository.findSouscriptionByIdevenement(id).isEmpty())
            throw new EntityDeletionException("Events can not be deleted", "Event is associated to more subscription");

        evenementRepository.deleteById(id);
        return true;
    }

    @Override
    public List<EvenementResponseDTO> getAllEvents() {
        log.info("Fetch all events");
        return evenementRepository
                .findAll()
                .stream()
                .map(EvenementMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EvenementResponseDTO> getAllByIdanne(int idAnnee) {
        log.info("Fetch all events with {id_annee}", idAnnee);
        return evenementRepository.findAllByAnneeOrderByIdEvenement(new Annee(idAnnee))
                .stream()
                .map(EvenementMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EvenementResponseDTO findById(Long id) {
        log.info("Fetch Event With id : ", id);
        Optional<Evenement> evenement = evenementRepository.findById(id);
        return evenement.map(EvenementMapper.INSTANCE::fromEntityToResponse).orElse(null);
    }

    private String generateCode(Integer length) {
        String rndChar = RandomStringUtils.randomAlphanumeric(length).toUpperCase();
        return "EVT" + rndChar + StringUtils.leftPad(nextId().toString(), 3, "0");
    }

    private Long nextId() {
        try {
            return evenementRepository.nextId() + 1;
        } catch (Exception e) {
            return 1L;
        }
    }
}
