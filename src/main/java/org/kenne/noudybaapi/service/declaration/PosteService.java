package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.dto.PosteRequestDTO;
import org.kenne.noudybaapi.dto.PosteResponseDTO;

import java.util.List;

public interface PosteService {

    List<PosteResponseDTO> findAll();

    PosteResponseDTO save(PosteRequestDTO requestDTO);

    PosteResponseDTO edit(PosteRequestDTO requestDTO);

    boolean delete(Integer id);

    PosteResponseDTO findById(Integer id);
}
