package org.kenne.noudybaapi.resource;

import lombok.RequiredArgsConstructor;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.dto.PayementSouscriptionRequestDTO;
import org.kenne.noudybaapi.dto.PayementSouscriptionResponseDTO;
import org.kenne.noudybaapi.service.declaration.PayementSouscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payement")
public class PayementSouscriptionController {

    private final PayementSouscriptionService service;

    @GetMapping("/list/all")
    public ResponseEntity<Response<List<PayementSouscriptionResponseDTO>>> findAll() {
        Response<List<PayementSouscriptionResponseDTO>> response = Response.<List<PayementSouscriptionResponseDTO>>builder()
                .data(service.getAll())
                .message("Payement list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Response<PayementSouscriptionResponseDTO>> save(@RequestBody @Valid PayementSouscriptionRequestDTO requestDTO) {
        Response<PayementSouscriptionResponseDTO> response = Response.<PayementSouscriptionResponseDTO>builder()
                .data(service.save(requestDTO))
                .message("Payement saved saved successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Response<PayementSouscriptionResponseDTO>> edit(@PathVariable("id") Long id, @RequestBody @Valid PayementSouscriptionRequestDTO requestDTO) {
        requestDTO.setIdPayementSouscription(id);
        Response<PayementSouscriptionResponseDTO> response = Response.<PayementSouscriptionResponseDTO>builder()
                .data(service.edit(requestDTO))
                .message("Payement edited successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response<PayementSouscriptionResponseDTO>> findById(@PathVariable("id") Long id) {
        PayementSouscriptionResponseDTO responseDto = service.findById(id);
        if (Objects.isNull(responseDto)) throw new EntityNotFoundException("Entity Not Found With Id " + id);

        Response<PayementSouscriptionResponseDTO> response = Response.<PayementSouscriptionResponseDTO>builder()
                .data(responseDto)
                .message("Payement fetched with id " + id)
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable("id") Long id) {
        boolean result = service.delete(id);
        Response<?> response = Response.builder()
                .message(result ? "Event deleted successfully" : "Event can not be deleted")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
