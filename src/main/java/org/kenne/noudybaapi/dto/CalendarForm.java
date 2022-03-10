package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalendarForm {
    @NotNull
    private Integer idAnnee;
    List<PeriodeRequestDTO> periods;
}
