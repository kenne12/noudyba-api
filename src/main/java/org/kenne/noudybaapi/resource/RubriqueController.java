package org.kenne.noudybaapi.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.dto.RubriqueRequestDTO;
import org.kenne.noudybaapi.dto.RubriqueResponseDTO;
import org.kenne.noudybaapi.service.declaration.RubriqueService;
import org.kenne.noudybaapi.util.UtilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rubrique")
@Tag(name = "RubricController")
public class RubriqueController {

    private final RubriqueService rubriqueService;

    @GetMapping("/list/all")
    public ResponseEntity<Response<List<RubriqueResponseDTO>>> findAll() {
        Response<List<RubriqueResponseDTO>> response = Response.<List<RubriqueResponseDTO>>builder()
                .datas(UtilService.getRubrics("rubriques", rubriqueService.findAll()))
                .message("Rubric list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Response<RubriqueResponseDTO>> save(@RequestBody @Valid RubriqueRequestDTO requestDTO) {
        final Response<RubriqueResponseDTO> response = Response.<RubriqueResponseDTO>builder()
                .datas(UtilService.getData("rubrique", rubriqueService.save(requestDTO)))
                .message("Rubric saved successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Response<RubriqueResponseDTO>> edit(@PathVariable("id") Integer id, @RequestBody @Valid RubriqueRequestDTO requestDTO) {
        requestDTO.setIdRubrique(id);
        Response<RubriqueResponseDTO> response = Response.<RubriqueResponseDTO>builder()
                .datas(UtilService.getData("rubrique", rubriqueService.edit(requestDTO)))
                .message("Rubric edited successfully with")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response<RubriqueResponseDTO>> findById(@PathVariable("id") Integer id) {
        RubriqueResponseDTO responseDto = rubriqueService.findById(id);
        if (Objects.isNull(responseDto)) throw new EntityNotFoundException("Rubric Not Found With {Id} " + id);
        Response<RubriqueResponseDTO> response = Response.<RubriqueResponseDTO>builder()
                .datas(UtilService.getData("rubrique", responseDto))
                .message("Rubric fetched successfully ")
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable("id") Integer id) {
        rubriqueService.delete(id);
        Response<?> response = Response.builder()
                .message("Rubric deleted successfully with")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
