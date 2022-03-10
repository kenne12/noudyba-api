package org.kenne.noudybaapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Datasets {
    private String label;
    private List<Double> data;

    public Datasets() {
        this.data = new ArrayList<>();
    }
}
