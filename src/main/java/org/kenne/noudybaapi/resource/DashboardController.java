package org.kenne.noudybaapi.resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.kenne.noudybaapi.common.Response;
import org.kenne.noudybaapi.dto.DashboardDataResponse;
import org.kenne.noudybaapi.service.declaration.DashboardService;
import org.kenne.noudybaapi.util.UtilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@AllArgsConstructor
@Tag(name = "DashboardController")
public class DashboardController {


    private final DashboardService dashboardService;

    @GetMapping("/get/{id_annee}")
    public ResponseEntity<Response<DashboardDataResponse>> getDatas(@PathVariable("id_annee") Integer idAnnee) {

        Response<DashboardDataResponse> response = Response.<DashboardDataResponse>builder()
                .datas(UtilService.getData("dashboard" , dashboardService.getData(idAnnee)))
                .message("Dashboard data successfully")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
