package org.kenne.noudybaapi.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.dto.AnneeRequestDTO;
import org.kenne.noudybaapi.dto.AnneeResponseDTO;
import org.kenne.noudybaapi.service.declaration.AnneeService;
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
@RequestMapping("/api/v1/annee")
@Tag(name = "AnneeController")
public class AnneeController {

    private final AnneeService anneeService;

    @GetMapping("/list/all")
    public ResponseEntity<Response<List<AnneeResponseDTO>>> findAll() {
        Response<List<AnneeResponseDTO>> response = Response.<List<AnneeResponseDTO>>builder()
                .datas(UtilService.getDatas("annees", anneeService.findAll()))
                .message("Annee list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/all/{etat}")
    public ResponseEntity<Response<List<AnneeResponseDTO>>> findAll(@PathVariable("etat") boolean etat) {
        Response<List<AnneeResponseDTO>> response = Response.<List<AnneeResponseDTO>>builder()
                .datas(UtilService.getDatas("annees", anneeService.findAll(etat)))
                .message("Annee list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Response<AnneeResponseDTO>> save(@RequestBody @Valid AnneeRequestDTO requestDTO) {
        final Response<AnneeResponseDTO> response = Response.<AnneeResponseDTO>builder()
                .datas(UtilService.getData("annee", anneeService.save(requestDTO)))
                .message("Annee saved successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Response<AnneeResponseDTO>> edit(@PathVariable("id") Integer id, @RequestBody @Valid AnneeRequestDTO requestDTO) {
        requestDTO.setIdAnnee(id);
        Response<AnneeResponseDTO> response = Response.<AnneeResponseDTO>builder()
                .datas(UtilService.getData("annee", anneeService.edit(requestDTO)))
                .message("Year edited successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response<AnneeResponseDTO>> findById(@PathVariable("id") Integer id) {
        AnneeResponseDTO responseDto = anneeService.findById(id);
        if (Objects.isNull(responseDto)) throw new EntityNotFoundException("Annee Not Found With Id : " + id);
        Response<AnneeResponseDTO> response = Response.<AnneeResponseDTO>builder()
                .datas(UtilService.getData("annee", responseDto))
                .message("Annee fetched successfully")
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable("id") Integer id) {
        anneeService.delete(id);
        Response<?> response = Response.builder()
                .message("Annee deleted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
