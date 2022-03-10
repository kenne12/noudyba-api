package org.kenne.noudybaapi.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.dto.SouscriptionRequestDTO;
import org.kenne.noudybaapi.dto.SouscriptionResponseDTO;
import org.kenne.noudybaapi.service.declaration.SouscriptionService;
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
@RequestMapping("/api/v1/subscription")
@Tag(name = "SubscriptionController")
public class SouscriptionController {

    private final SouscriptionService souscriptionService;

    @GetMapping("/list/all")
    public ResponseEntity<Response<List<SouscriptionResponseDTO>>> findAll() {
        Response<List<SouscriptionResponseDTO>> response = Response.<List<SouscriptionResponseDTO>>builder()
                .datas(UtilService.getSubs("subscriptions", souscriptionService.getAllSouscription()))
                .message("Subscriptions list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/all/{id_annee}")
    public ResponseEntity<Response<List<SouscriptionResponseDTO>>> findAllByIdannee(@PathVariable("id_annee") Integer id_annee) {
        Response<List<SouscriptionResponseDTO>> response = Response.<List<SouscriptionResponseDTO>>builder()
                .datas(UtilService.getSubs("subscriptions", souscriptionService.getAllByIdanne(id_annee)))
                .message("Subscriptions list fetch successfully with {id_annee}")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/not_paid_by/member/{id_membre}/annee/{id_annee}")
    public ResponseEntity<Response<List<SouscriptionResponseDTO>>>
    findSubsNotPaidByMemberAndAnnee(@PathVariable("id_membre") Long id_membre,
                                    @PathVariable("id_annee") Integer id_annee) {
        Response<List<SouscriptionResponseDTO>> response = Response.<List<SouscriptionResponseDTO>>builder()
                .datas(UtilService.getSubs("subscriptions", souscriptionService.getAllNotPaidSubscriptionByMember(id_membre, id_annee)))
                .message("Subscriptions list fetch successfully with {id_membre} and {id_annee}")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/not_paid_by/annee/{id_annee}")
    public ResponseEntity<Response<List<SouscriptionResponseDTO>>>
    findSubsNotPaidByAnnee(@PathVariable("id_annee") Integer id_annee) {
        Response<List<SouscriptionResponseDTO>> response = Response.<List<SouscriptionResponseDTO>>builder()
                .datas(UtilService.getSubs("subscriptions", souscriptionService.getAllNotPaidSubscriptionByAnnee(id_annee)))
                .message("Subscriptions list fetch successfully with {id_annee}")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<Response<SouscriptionResponseDTO>> save(@RequestBody @Valid SouscriptionRequestDTO requestDTO) {
        Response<SouscriptionResponseDTO> response = Response.<SouscriptionResponseDTO>builder()
                .datas(UtilService.getData("subscription", souscriptionService.save(requestDTO)))
                .message("Subscription saved successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Response<SouscriptionResponseDTO>> edit(@PathVariable("id") Long id, @RequestBody @Valid SouscriptionRequestDTO requestDTO) {
        requestDTO.setIdSouscription(id);
        Response<SouscriptionResponseDTO> response = Response.<SouscriptionResponseDTO>builder()
                .datas(UtilService.getData("subscription", souscriptionService.edit(requestDTO)))
                .message("Subscription edited successfully with {Id}")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response<SouscriptionResponseDTO>> findById(@PathVariable("id") Long id) {
        SouscriptionResponseDTO responseDto = souscriptionService.findById(id);
        if (Objects.isNull(responseDto)) throw new EntityNotFoundException("Subscription not found with {Id} " + id);

        Response<SouscriptionResponseDTO> response = Response.<SouscriptionResponseDTO>builder()
                .datas(UtilService.getData("subscription", responseDto))
                .message("Subscription fetched successfully")
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable("id") Long id) {
        souscriptionService.delete(id);
        Response<?> response = Response.builder()
                .message("Subscription deleted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
