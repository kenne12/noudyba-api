package org.kenne.noudybaapi.service.imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kenne.noudybaapi.domain.Poste;
import org.kenne.noudybaapi.dto.PosteRequestDTO;
import org.kenne.noudybaapi.dto.PosteResponseDTO;
import org.kenne.noudybaapi.mapper.PosteMapper;
import org.kenne.noudybaapi.repository.PosteRepository;
import org.kenne.noudybaapi.service.declaration.PosteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PosteServiceImpl implements PosteService {

    private final PosteRepository posteRepository;

    @Override
    public List<PosteResponseDTO> findAll() {
        log.info("Fetch all poste");
        return posteRepository.findAll()
                .stream()
                .map(PosteMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PosteResponseDTO save(PosteRequestDTO requestDTO) {
        log.info("Save new poste {}");
        Poste poste = PosteMapper.INSTANCE.fromRequestToEntity(requestDTO);
        return PosteMapper.INSTANCE.fromEntityToResponse(posteRepository.save(poste));
    }

    @Override
    public PosteResponseDTO edit(PosteRequestDTO requestDTO) {
        log.info("Edit post with {Id} ", requestDTO.getIdPoste());
        Poste poste = PosteMapper.INSTANCE.fromRequestToEntity(requestDTO);
        return PosteMapper.INSTANCE.fromEntityToResponse(posteRepository.save(poste));
    }

    @Override
    public boolean delete(Integer id) {
        log.info("Delete poste with {Id} ", id);
        posteRepository.deleteById(id);
        return true;
    }

    @Override
    public PosteResponseDTO findById(Integer id) {
        log.info("Fetch poste with {Id} ", id);
        Optional<Poste> poste = posteRepository.findById(id);
        return poste.map(PosteMapper.INSTANCE::fromEntityToResponse).orElse(null);
    }
}
