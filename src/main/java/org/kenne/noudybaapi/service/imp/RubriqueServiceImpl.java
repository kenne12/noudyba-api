package org.kenne.noudybaapi.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.kenne.noudybaapi.domain.Rubrique;
import org.kenne.noudybaapi.dto.RubriqueRequestDTO;
import org.kenne.noudybaapi.dto.RubriqueResponseDTO;
import org.kenne.noudybaapi.mapper.RubriqueMapper;
import org.kenne.noudybaapi.repository.EvenementRepository;
import org.kenne.noudybaapi.repository.RubriqueRepository;
import org.kenne.noudybaapi.service.declaration.RubriqueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RubriqueServiceImpl implements RubriqueService {

    private final RubriqueRepository rubriqueRepository;
    private final EvenementRepository evenementRepository;

    @Override
    public RubriqueResponseDTO save(RubriqueRequestDTO requestDTO) {
        log.info("Save new Rubric {}");
        Rubrique rubrique = RubriqueMapper.INSTANCE.fromRequestToEntity(requestDTO);
        Integer nextId = this.nextId();
        rubrique.setCode(this.generateCode(nextId));
        rubrique.setIdRubrique(nextId);
        return RubriqueMapper.INSTANCE.fromEntityToResponse(rubriqueRepository.save(rubrique));
    }

    @Override
    public RubriqueResponseDTO edit(RubriqueRequestDTO requestDTO) {
        log.info("Edit Rubric with {Id}", requestDTO.getIdRubrique());
        if (requestDTO.getIdRubrique() != null) {
            Rubrique rubrique = RubriqueMapper.INSTANCE.fromRequestToEntity(requestDTO);
            return RubriqueMapper.INSTANCE.fromEntityToResponse(rubriqueRepository.save(rubrique));
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        log.info("Delete Rubric with {Id} ", id);
        if (evenementRepository.findAllByIdrubrique(id).isEmpty()) {
            rubriqueRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public RubriqueResponseDTO findById(Integer id) {
        log.info("Fetch Rubric By {Id} ", id);
        Optional<Rubrique> rubrique = rubriqueRepository.findById(id);
        return rubrique.map(RubriqueMapper.INSTANCE::fromEntityToResponse).orElse(null);
    }

    @Override
    public List<RubriqueResponseDTO> findAll() {
        return rubriqueRepository.findAll()
                .stream()
                .map(RubriqueMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    private Integer nextId() {
        try {
            return (rubriqueRepository.nextId() + 1);
        } catch (Exception e) {
            return 1;
        }
    }

    private String generateCode(Integer nextId) {
        final String code = "R" + RandomStringUtils.randomAlphanumeric(1).toUpperCase() + StringUtils.leftPad(nextId().toString(), 2, '0');
        return code;
    }
}
