package org.kenne.noudybaapi.service.imp;

import lombok.AllArgsConstructor;
import org.kenne.noudybaapi.domain.Evenement;
import org.kenne.noudybaapi.domain.Membre;
import org.kenne.noudybaapi.domain.PayementSouscription;
import org.kenne.noudybaapi.domain.Souscription;
import org.kenne.noudybaapi.dto.SouscriptionRequestDTO;
import org.kenne.noudybaapi.dto.SouscriptionResponseDTO;
import org.kenne.noudybaapi.exception.EntityDeletionException;
import org.kenne.noudybaapi.mapper.SouscriptionMapper;
import org.kenne.noudybaapi.repository.EvenementRepository;
import org.kenne.noudybaapi.repository.MembreRepository;
import org.kenne.noudybaapi.repository.PayementSouscriptionRepository;
import org.kenne.noudybaapi.repository.SouscriptionRepository;
import org.kenne.noudybaapi.service.declaration.SouscriptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SouscriptionServiceImpl implements SouscriptionService {

    private final SouscriptionRepository souscriptionRepository;
    private final EvenementRepository evenementRepository;
    private final MembreRepository membreRepository;
    private final PayementSouscriptionRepository payementSouscriptionRepository;

    @Override
    public SouscriptionResponseDTO save(SouscriptionRequestDTO requestDTO) {

        Membre membre = membreRepository.findById(requestDTO.getIdMembre())
                .orElseThrow(() -> new org.kenne.noudybaapi.exception.EntityNotFoundException("Member not found with id :" + requestDTO.getIdMembre()));

        Evenement evenement = evenementRepository.findById(requestDTO.getIdEvenement())
                .orElseThrow(() -> new org.kenne.noudybaapi.exception.EntityNotFoundException("Member not found with id : " + requestDTO.getIdEvenement()));

        Souscription souscription = SouscriptionMapper.INSTANCE.fromRequestToEntity(requestDTO);
        souscription.setEvenement(evenement);
        souscription.setMembre(membre);
        souscription.setMontantPaye(0d);
        souscription.setDateSouscription(Date.from(Instant.now()));
        souscription.setIdSouscription(this.nexId());
        return SouscriptionMapper.INSTANCE.fromEntityToResponse(souscriptionRepository.save(souscription));
    }

    @Override
    public SouscriptionResponseDTO edit(SouscriptionRequestDTO requestDTO) {
        List<PayementSouscription> list =
                payementSouscriptionRepository.findPayementSouscriptionByIdsouscription(requestDTO.getIdSouscription());

        if (!list.isEmpty())
            throw new EntityNotFoundException(
                    "Souscription not found with id : " + requestDTO.getIdSouscription());

        Souscription souscriptionOld = souscriptionRepository.getById(requestDTO.getIdSouscription());
        souscriptionOld.setMontant(requestDTO.getMontant());
        souscriptionOld.setDateSouscription(requestDTO.getDateSouscription());
        souscriptionOld.setMembre(membreRepository.getById(requestDTO.getIdMembre()));
        souscriptionOld.setEvenement(evenementRepository.getById(requestDTO.getIdEvenement()));
        return SouscriptionMapper.INSTANCE.fromEntityToResponse(souscriptionRepository.save(souscriptionOld));
    }

    @Override
    public boolean delete(Long id) {
        if (!payementSouscriptionRepository.findPayementSouscriptionByIdsouscription(id).isEmpty())
            throw new EntityDeletionException(
                    "Souscription cannot be deleted with id : " + id,
                    "Souscription is associated to more payement");

        souscriptionRepository.deleteById(id);
        return false;
    }

    @Override
    public List<SouscriptionResponseDTO> getAllSouscription() {
        return souscriptionRepository.findAll()
                .stream()
                .map(SouscriptionMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SouscriptionResponseDTO findById(Long id) {
        Optional<Souscription> souscription = souscriptionRepository.findById(id);
        return souscription.map(SouscriptionMapper.INSTANCE::fromEntityToResponse).orElse(null);
    }

    private Long nexId() {
        try {
            return (souscriptionRepository.nexId() + 1);
        } catch (Exception e) {
            return 1l;
        }
    }
}
