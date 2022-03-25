package org.kenne.noudybaapi.service.imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kenne.noudybaapi.domain.Ville;
import org.kenne.noudybaapi.dto.VilleRequestDTO;
import org.kenne.noudybaapi.dto.VilleResponseDTO;
import org.kenne.noudybaapi.exception.EntityNotFoundException;
import org.kenne.noudybaapi.mapper.VilleMapper;
import org.kenne.noudybaapi.repository.VilleRepository;
import org.kenne.noudybaapi.service.declaration.VilleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class VilleServiceImpl implements VilleService {


    private final VilleRepository villeRepository;

    @Override
    public List<VilleResponseDTO> findAll() {
        log.info("Fetch all villes");
        return villeRepository.findAll()
                .stream()
                .map(VilleMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VilleResponseDTO findById(Integer id) {
        villeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ville not found with id : " + id));
        Optional<Ville> ville = villeRepository.findById(id);
        return ville.map(VilleMapper.INSTANCE::fromEntityToResponse).orElse(null);
    }

    @Override
    public VilleResponseDTO save(VilleRequestDTO requestDTO) {
        log.info("Save new Ville {}");
        Ville ville = VilleMapper.INSTANCE.fromRequestToEntity(requestDTO);
        ville.setIdVille(this.nextId());
        Ville savedVille = villeRepository.save(ville);
        return VilleMapper.INSTANCE.fromEntityToResponse(savedVille);
    }

    @Override
    public VilleResponseDTO edit(VilleRequestDTO requestDTO) {
        log.info("Edit ville {id} ", requestDTO.getIdVille());
        villeRepository.findById(requestDTO.getIdVille())
                .orElseThrow(() -> new EntityNotFoundException("Ville not found with id : " + requestDTO.getIdVille()));
        Ville ville = VilleMapper.INSTANCE.fromRequestToEntity(requestDTO);
        return VilleMapper.INSTANCE.fromEntityToResponse(villeRepository.save(ville));
    }

    @Override
    public boolean delete(Integer id) {
        log.info("Delete ville {id}", id);
        villeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ville not found with id : " + id));
        villeRepository.deleteById(id);
        return true;
    }

    private Integer nextId() {
        try {
            return (villeRepository.nextId() + 1);
        } catch (Exception e) {
            return 1;
        }
    }
}
