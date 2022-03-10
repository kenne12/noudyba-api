package org.kenne.noudybaapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardDataResponse {

    Map<String, DashboardData> charData;
    List<OperationResponseDTO> operations;
    List<Object[]> contributions;

    private Double pourcentageParticipation;
    private Integer nombreMembres;
}
