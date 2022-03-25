package org.kenne.noudybaapi.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.dto.PosteRequestDTO;
import org.kenne.noudybaapi.dto.PosteResponseDTO;
import org.kenne.noudybaapi.service.declaration.PosteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/poste")
@Tag(name = "PosteController")
public class PosteController {

    private final PosteService posteService;

    @GetMapping("/list/all")
    public ResponseEntity<Response<List<PosteResponseDTO>>> findAll() {
        Response<List<PosteResponseDTO>> response = Response.<List<PosteResponseDTO>>builder()
                .data(posteService.findAll())
                .message("Postes list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Response<PosteResponseDTO>> save(@RequestBody @Valid PosteRequestDTO requestDTO) {
        final Response<PosteResponseDTO> response = Response.<PosteResponseDTO>builder()
                .data(posteService.save(requestDTO))
                .message("Poste saved successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Response<PosteResponseDTO>> edit(@PathVariable("id") Integer id, @RequestBody @Valid PosteRequestDTO requestDTO) {
        requestDTO.setIdPoste(id);
        Response<PosteResponseDTO> response = Response.<PosteResponseDTO>builder()
                .data(posteService.edit(requestDTO))
                .message("Poste edited successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response<PosteResponseDTO>> findById(@PathVariable("id") Integer id) {
        PosteResponseDTO responseDto = posteService.findById(id);
        if (Objects.isNull(responseDto)) throw new EntityNotFoundException("Entity Not Found With Id " + id);
        Response<PosteResponseDTO> response = Response.<PosteResponseDTO>builder()
                .data(responseDto)
                .message("Poste fetched with successfully" )
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable("id") Integer id) {
        posteService.delete(id);
        Response<?> response = Response.builder()
                .message("Poste deleted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
