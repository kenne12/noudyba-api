package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.domain.Mois;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CalendarDataReturn {
    List<PeriodeResponse> periods = new ArrayList<>();
    List<Mois> months = new ArrayList<>();
}
