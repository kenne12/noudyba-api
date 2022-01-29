package org.kenne.noudybaapi.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.dto.MembreRequestDTO;
import org.kenne.noudybaapi.dto.MembreResponseDTO;
import org.kenne.noudybaapi.service.declaration.MembreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/membre")
@Tag(name = "MemberController")
public class MembreController {

    private final MembreService membreService;

    @GetMapping("/list/all")
    public ResponseEntity<Response<List<MembreResponseDTO>>> findAll() {
        Response<List<MembreResponseDTO>> response = Response.<List<MembreResponseDTO>>builder()
                .data(membreService.getAllMembers())
                .message("Members list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Response<MembreResponseDTO>> save(@RequestBody @Valid MembreRequestDTO requestDTO) throws Exception {
        Response<MembreResponseDTO> response = Response.<MembreResponseDTO>builder()
                .data(membreService.save(requestDTO))
                .message("Member saved successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Response<MembreResponseDTO>> edit(@PathVariable("id") Long id, @RequestBody @Valid MembreRequestDTO requestDTO) throws Exception {
        requestDTO.setIdMembre(id);
        Response<MembreResponseDTO> response = Response.<MembreResponseDTO>builder()
                .data(membreService.edit(requestDTO))
                .message("Member edited successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response<MembreResponseDTO>> findById(@PathVariable("id") Long id) {
        MembreResponseDTO responseDto = membreService.findById(id);
        if (Objects.isNull(responseDto)) throw new EntityNotFoundException("Member not found with Id : " + id);

        Response<MembreResponseDTO> response = Response.<MembreResponseDTO>builder()
                .data(responseDto)
                .message("Member fetched successfully")
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable("id") Long id) {
        membreService.delete(id);
        Response<?> response = Response.builder()
                .message("Member deleted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public byte[] getImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/resource_projet/noudyba_resource/image/" + fileName));
    }
}
