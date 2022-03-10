package org.kenne.noudybaapi.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.dto.*;
import org.kenne.noudybaapi.service.declaration.PeriodeService;
import org.kenne.noudybaapi.util.UtilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/calendrier")
@RequiredArgsConstructor
@Tag(name = "CalendrierController")
public class CalendrierController {


    private final PeriodeService periodeService;


    @GetMapping("/list/all/{annee_id}")
    public ResponseEntity<Response<List<PeriodeResponse>>> find_all_by_annee(@PathVariable("annee_id") Integer anneeId) {
        Response<List<PeriodeResponse>> response = Response.<List<PeriodeResponse>>builder()
                .datas(UtilService.getPeriods("periods", periodeService.findAllByAnneeId(anneeId)))
                .message("Periods list fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/calendar_data/{annee_id}")
    public ResponseEntity<Response<CalendarDataReturn>> find_month_to_save(@PathVariable("annee_id") Integer anneeId) {
        Response<CalendarDataReturn> response = Response.<CalendarDataReturn>builder()
                .datas(UtilService.getDataCalendar("calendar", periodeService.getLeftMonthByAnneeId(anneeId)))
                .message("Data to save fetch successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }


    @PostMapping("/save")
    public ResponseEntity<Response<CalendarDataReturn>> saves(@RequestBody @Valid CalendarForm calendarForm) {
        Response<CalendarDataReturn> response = Response.<CalendarDataReturn>builder()
                .datas(UtilService.getDataCalendar("calendar", periodeService.save(calendarForm)))
                .message("Periods saved successfully")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<Response<PeriodeResponse>> edit(@RequestBody @Valid PeriodeRequestDTO requestDTO) {
        Response<PeriodeResponse> response = Response.<PeriodeResponse>builder()
                .datas(UtilService.getData("period", periodeService.edit(requestDTO)))
                .message("Period edited successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get/{annee_id}/{mois_id}")
    public ResponseEntity<Response<PeriodeResponse>> findById(@PathVariable("annee_id") Integer anneeId, @PathVariable("mois_id") Integer moisId) {
        PeriodeResponse responseDto = periodeService.get(new PeriodePkForm(anneeId, moisId));
        if (Objects.isNull(responseDto)) throw new EntityNotFoundException("Period Not Found");

        Response<PeriodeResponse> response = Response.<PeriodeResponse>builder()
                .datas(UtilService.getData("period", responseDto))
                .message("Period fetched successfully")
                .status(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{annee_id}/{mois_id}")
    public ResponseEntity<Response<?>> delete(@PathVariable("annee_id") Integer anneeId, @PathVariable("mois_id") Integer moisId) {
        periodeService.delete(new PeriodePkForm(anneeId, moisId));
        Response<?> response = Response.builder()
                .message("Period deleted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deletes")
    public ResponseEntity<Response<?>> deletes(@RequestBody List<PeriodePkForm> ids) {
        periodeService.delete(ids);
        Response<?> response = Response.builder()
                .message("Periods deleted successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

}
