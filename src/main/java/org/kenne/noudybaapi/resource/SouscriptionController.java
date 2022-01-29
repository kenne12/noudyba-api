package org.kenne.noudybaapi.resource;

import lombok.RequiredArgsConstructor;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.dto.SouscriptionRequestDTO;
import org.kenne.noudybaapi.dto.SouscriptionResponseDTO;
import org.kenne.noudybaapi.service.declaration.SouscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscription")
public class SouscriptionController {

    private final SouscriptionService souscriptionService;

    @GetMapping("/list/all")
    public ResponseEntity<Response<List<SouscriptionResponseDTO>>> findAll() {
        Response<List<SouscriptionResponseDTO>> response = Response.<List<SouscriptionResponseDTO>>builder()
                .data(souscriptionService.getAllSouscription())
                .message("Events list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Response<SouscriptionResponseDTO>> save(@RequestBody @Valid SouscriptionRequestDTO requestDTO) {
        Response<SouscriptionResponseDTO> response = Response.<SouscriptionResponseDTO>builder()
                .data(souscriptionService.save(requestDTO))
                .message("Subscription saved saved successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Response<SouscriptionResponseDTO>> edit(@PathVariable("id") Long id, @RequestBody @Valid SouscriptionRequestDTO requestDTO) {
        requestDTO.setIdEvenement(id);
        Response<SouscriptionResponseDTO> response = Response.<SouscriptionResponseDTO>builder()
                .data(souscriptionService.edit(requestDTO))
                .message("Souscription edited successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response<SouscriptionResponseDTO>> findById(@PathVariable("id") Long id) {
        SouscriptionResponseDTO responseDto = souscriptionService.findById(id);
        if (Objects.isNull(responseDto)) throw new EntityNotFoundException("Subscription not found with Id " + id);

        Response<SouscriptionResponseDTO> response = Response.<SouscriptionResponseDTO>builder()
                .data(responseDto)
                .message("Souscription fetched successfully")
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable("id") Long id) {
        souscriptionService.delete(id);
        Response<?> response = Response.builder()
                .message("Souscription deleted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
