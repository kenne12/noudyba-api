package org.kenne.noudybaapi.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.dto.ContributionRequestDTO;
import org.kenne.noudybaapi.dto.ContributionResponseDTO;
import org.kenne.noudybaapi.service.declaration.ContributionService;
import org.kenne.noudybaapi.util.UtilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contribution")
@Tag(name = "ContributionDirectController")
public class ContributionController {

    private final ContributionService service;

    @GetMapping("/list/all")
    public ResponseEntity<Response<List<ContributionResponseDTO>>> findAll() {
        Response<List<ContributionResponseDTO>> response = Response.<List<ContributionResponseDTO>>builder()
                .datas(UtilService.getContributions("contributions", service.getAll()))
                .message("Contribution list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/all/{id_annee}")
    public ResponseEntity<Response<List<ContributionResponseDTO>>> findAllByIdannee(@PathVariable("id_annee") Integer id) {
        Response<List<ContributionResponseDTO>> response = Response.<List<ContributionResponseDTO>>builder()
                .datas(UtilService.getContributions("contributions", service.getAllByIdannee(id)))
                .message("Contribution list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Response<ContributionResponseDTO>> save(@RequestBody @Valid ContributionRequestDTO requestDTO) {
        Response<ContributionResponseDTO> response = Response.<ContributionResponseDTO>builder()
                .datas(UtilService.getData("contribution", service.save(requestDTO)))
                .message("Contribution saved successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Response<ContributionResponseDTO>> edit(@PathVariable("id") Long id, @RequestBody @Valid ContributionRequestDTO requestDTO) {
        requestDTO.setIdContribution(id);
        Response<ContributionResponseDTO> response = Response.<ContributionResponseDTO>builder()
                .datas(UtilService.getData("contribution", service.edit(requestDTO)))
                .message("Contribution edited successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response<ContributionResponseDTO>> findById(@PathVariable("id") Long id) {
        Response<ContributionResponseDTO> response = Response.<ContributionResponseDTO>builder()
                .datas(UtilService.getData("contribution", service.findById(id)))
                .message("Contribution fetched successfully")
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable("id") Long id) {
        service.delete(id);
        Response<?> response = Response.builder()
                .message("Contribution deleted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
