package org.kenne.noudybaapi.service.declaration;

import org.kenne.noudybaapi.domain.Mois;
import org.kenne.noudybaapi.dto.*;

import java.util.List;

public interface PeriodeService {

    List<PeriodeResponse> findAllByAnneeId(int anneeId);

    Mois saveMois(Mois mois);


    PeriodeResponse get(PeriodePkForm periodePkForm);

    PeriodeResponse edit(PeriodeRequestDTO requestDTO);


    CalendarDataReturn save(CalendarForm calendarForm);

    List<PeriodeResponse> edits(List<PeriodeRequestDTO> requestDTOS);

    CalendarDataReturn getLeftMonthByAnneeId(int anneeId);

    void delete(PeriodePkForm id);

    void delete(List<PeriodePkForm> ids);

}
