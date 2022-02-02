package org.kenne.noudybaapi.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.dto.EvenementRequestDTO;
import org.kenne.noudybaapi.dto.EvenementResponseDTO;
import org.kenne.noudybaapi.service.declaration.EvenementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/evenement")
@Tag(name = "EventController")
public class EvenementController {

    private final EvenementService evenementService;

    @GetMapping("/list/all")
    public ResponseEntity<Response<List<EvenementResponseDTO>>> findAll() {
        Response<List<EvenementResponseDTO>> response = Response.<List<EvenementResponseDTO>>builder()
                .data(evenementService.getAllEvents())
                .message("Events list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/all/{id_annee}")
    public ResponseEntity<Response<List<EvenementResponseDTO>>> find_all_by_annee(@PathVariable("id_annee") Integer id_annee) {
        Response<List<EvenementResponseDTO>> response = Response.<List<EvenementResponseDTO>>builder()
                .data(evenementService.getAllByIdanne(id_annee))
                .message("Events list fetch successfully with id")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Response<EvenementResponseDTO>> save(@RequestBody @Valid EvenementRequestDTO requestDTO) {
        Response<EvenementResponseDTO> response = Response.<EvenementResponseDTO>builder()
                .data(evenementService.save(requestDTO))
                .message("Event saved saved successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Response<EvenementResponseDTO>> edit(@PathVariable("id") Long id, @RequestBody @Valid EvenementRequestDTO requestDTO) {
        requestDTO.setIdEvenement(id);
        Response<EvenementResponseDTO> response = Response.<EvenementResponseDTO>builder()
                .data(evenementService.edit(requestDTO))
                .message("Event edited successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response<EvenementResponseDTO>> findById(@PathVariable("id") Long id) {
        EvenementResponseDTO responseDto = evenementService.findById(id);
        if (Objects.isNull(responseDto)) throw new EntityNotFoundException("Entity Not Found With Id " + id);

        Response<EvenementResponseDTO> response = Response.<EvenementResponseDTO>builder()
                .data(responseDto)
                .message("Event fetched with id " + id)
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable("id") Long id) {
        evenementService.delete(id);
        Response<?> response = Response.builder()
                .message("Event deleted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
