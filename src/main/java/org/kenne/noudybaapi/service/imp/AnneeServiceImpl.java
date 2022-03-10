package org.kenne.noudybaapi.service.imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kenne.noudybaapi.domain.Annee;
import org.kenne.noudybaapi.dto.AnneeRequestDTO;
import org.kenne.noudybaapi.dto.AnneeResponseDTO;
import org.kenne.noudybaapi.exception.EntityDeletionException;
import org.kenne.noudybaapi.exception.EntityNotFoundException;
import org.kenne.noudybaapi.mapper.AnneeMapper;
import org.kenne.noudybaapi.repository.AnneeRepository;
import org.kenne.noudybaapi.repository.EvenementRepository;
import org.kenne.noudybaapi.service.declaration.AnneeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AnneeServiceImpl implements AnneeService {

    private AnneeRepository anneeRepository;
    private EvenementRepository evenementRepository;

    @Override
    public AnneeResponseDTO save(AnneeRequestDTO requestDTO) {
        log.info("Save new ville {}");
        Annee annee = AnneeMapper.INSTANCE.fromRequestToEntity(requestDTO);
        annee.setEtat(true);
        annee.setCloturee(false);
        annee.setIdAnnee(this.nextId());
        return AnneeMapper.INSTANCE.fromEntityToResponse(anneeRepository.save(annee));
    }

    @Override
    public AnneeResponseDTO edit(AnneeRequestDTO requestDTO) {
        log.info("Edit ville with {id} ", requestDTO.getIdAnnee());
        anneeRepository.findById(requestDTO.getIdAnnee())
                .orElseThrow(() -> new EntityNotFoundException("Annee not found with id : " + requestDTO.getIdAnnee()));
        Annee annee = AnneeMapper.INSTANCE.fromRequestToEntity(requestDTO);
        return AnneeMapper.INSTANCE.fromEntityToResponse(anneeRepository.save(annee));
    }

    @Override
    public boolean delete(Integer id) {
        log.info("Delete annee with {id} ", id);

        if (!evenementRepository.findAllByIdannee(id).isEmpty())
            throw new EntityDeletionException("Année can not be deleted with id" + id, "Année is associated to more events");
        anneeRepository.deleteById(id);
        return true;
    }

    @Override
    public List<AnneeResponseDTO> findAll() {
        log.info("Fetch all annees");
        //Sort.by("date_debut").and(Sort.by("date_fin"))
        return anneeRepository.findAll(Sort.by("dateDebut").and(Sort.by("dateFin")))
                .stream()
                .map(AnneeMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnneeResponseDTO> findAll(boolean etat) {
        log.info("Fetch all annees with {state} ", etat);
        return anneeRepository.findAllByEtat(etat, Sort.by("dateDebut").and(Sort.by("dateFin")))
                .stream()
                .map(AnneeMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AnneeResponseDTO findById(Integer id) {
        log.info("Fetch annee with {id} ", id);
        Optional<Annee> annee = anneeRepository.findById(id);
        return annee.map(AnneeMapper.INSTANCE::fromEntityToResponse).orElse(null);
    }

    private Integer nextId() {
        try {
            return (anneeRepository.nextId() + 1);
        } catch (Exception e) {
            return 1;
        }
    }
}
