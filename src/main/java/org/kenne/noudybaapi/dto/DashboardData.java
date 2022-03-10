package org.kenne.noudybaapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardData {
    private List<String> labels;
    private List<Datasets> datasets;

    public DashboardData() {
        this.labels = new ArrayList<>();
        this.datasets = new ArrayList<>();
    }
}


