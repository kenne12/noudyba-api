package org.kenne.noudybaapi.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.dto.VilleRequestDTO;
import org.kenne.noudybaapi.dto.VilleResponseDTO;
import org.kenne.noudybaapi.service.declaration.VilleService;
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
@RequestMapping("/api/v1/ville")
@Tag(name = "VilleController")
public class VilleController {

    private final VilleService villeService;


    @GetMapping("/list/all")
    public ResponseEntity<Response<List<VilleResponseDTO>>> findAll() {
        List<VilleResponseDTO> list = villeService.findAll();
        Response<List<VilleResponseDTO>> response = Response.<List<VilleResponseDTO>>builder()
                .datas(UtilService.getVilles("villes", list))
                .message("Villes list fetched successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Response<VilleResponseDTO>> save(@RequestBody @Valid VilleRequestDTO requestDTO) {
        VilleResponseDTO saved = villeService.save(requestDTO);
        final Response<VilleResponseDTO> response = Response.<VilleResponseDTO>builder()
                .datas(UtilService.getData("ville", saved))
                .message("Ville saved successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Response<VilleResponseDTO>> edit(@PathVariable("id") Integer id, @RequestBody @Valid VilleRequestDTO requestDTO) {
        requestDTO.setIdVille(id);
        VilleResponseDTO edited = villeService.edit(requestDTO);
        Response<VilleResponseDTO> response = Response.<VilleResponseDTO>builder()
                .datas(UtilService.getData("ville", edited))
                .message("Ville edited successfully with {id} "+id)
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response<VilleResponseDTO>> findById(@PathVariable("id") Integer id) {
        VilleResponseDTO responseDto = villeService.findById(id);
        if (Objects.isNull(responseDto)) throw new EntityNotFoundException("Ville not found with Id : " + id);

        Response<VilleResponseDTO> response = Response.<VilleResponseDTO>builder()
                .datas(UtilService.getData("ville", responseDto))
                .message("Ville fetched successfully with {id} " + id)
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable("id") Integer id) {
        villeService.delete(id);
        Response<?> response = Response.builder()
                .message("Ville deleted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

}
