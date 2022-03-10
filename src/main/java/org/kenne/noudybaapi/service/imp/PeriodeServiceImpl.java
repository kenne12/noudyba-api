package org.kenne.noudybaapi.service.imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kenne.noudybaapi.domain.Annee;
import org.kenne.noudybaapi.domain.Mois;
import org.kenne.noudybaapi.domain.Periode;
import org.kenne.noudybaapi.domain.PeriodePK;
import org.kenne.noudybaapi.dto.*;
import org.kenne.noudybaapi.exception.EntityNotFoundException;
import org.kenne.noudybaapi.mapper.PeriodeMapper;
import org.kenne.noudybaapi.repository.MoisRepository;
import org.kenne.noudybaapi.repository.PeriodeRepository;
import org.kenne.noudybaapi.service.declaration.PeriodeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class PeriodeServiceImpl implements PeriodeService {

    private final PeriodeRepository periodeRepository;
    private final MoisRepository moisRepository;

    @Override
    public List<PeriodeResponse> findAllByAnneeId(int anneeId) {
        return periodeRepository
                .findAllByAnnee(new Annee(anneeId), Sort.by("numero"))
                .stream().map(PeriodeMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Mois saveMois(Mois mois) {
        return moisRepository.save(mois);
    }


    @Override
    public PeriodeResponse get(PeriodePkForm periodePkForm) {
        Optional<Periode> periode = periodeRepository.findById(new PeriodePK(periodePkForm.getIdAnnee(), periodePkForm.getIdMois()));
        return periode.isPresent() ? PeriodeMapper.INSTANCE.fromEntityToResponse(periode.get()) : null;
    }

    @Override
    public PeriodeResponse edit(PeriodeRequestDTO requestDTO) {
        if (!periodeRepository.findById(new PeriodePK(requestDTO.getIdAnnee(), requestDTO.getIdMois())).isPresent())
            throw new EntityNotFoundException("Period not found");

        Periode periode = PeriodeMapper.INSTANCE.fromRequestToEntity(requestDTO);
        periode.setPeriodePK(new PeriodePK(requestDTO.getIdAnnee(), requestDTO.getIdMois()));
        return PeriodeMapper.INSTANCE.fromEntityToResponse(periodeRepository.save(periode));
    }


    @Override
    public CalendarDataReturn save(CalendarForm form) {
        List<PeriodeResponse> list = new ArrayList<>();

        form.getPeriods().stream().forEach(item -> {
            if (item.getDateFin().after(item.getDateDebut())) {
                Periode periode = PeriodeMapper.INSTANCE.fromRequestToEntity(item);
                periode.setPeriodePK(new PeriodePK(item.getIdMois(), form.getIdAnnee()));
                periode.setAnnee(new Annee(form.getIdAnnee()));
                periode.setMois(new Mois(item.getIdMois()));
                try {
                    list.add(PeriodeMapper.INSTANCE.fromEntityToResponse(periodeRepository.save(periode)));
                } catch (Exception e) {

                }
            }
        });
        return this.getLeftMonthByAnneeId(form.getIdAnnee());
    }

    @Override
    public List<PeriodeResponse> edits(List<PeriodeRequestDTO> requestDTOS) {
        List<PeriodeResponse> list = new ArrayList<>();
        requestDTOS.stream().forEach(item -> {
            if (periodeRepository.findById(new PeriodePK(item.getIdAnnee(), item.getIdMois())).isPresent()) {
                Periode periode = PeriodeMapper.INSTANCE.fromRequestToEntity(item);
                periode.setPeriodePK(new PeriodePK(item.getIdAnnee(), item.getIdMois()));
                list.add(PeriodeMapper.INSTANCE.fromEntityToResponse(periodeRepository.save(periode)));
            }
        });
        return list;
    }

    @Override
    public CalendarDataReturn getLeftMonthByAnneeId(int anneeId) {
        return CalendarDataReturn.builder()
                .periods(this.findAllByAnneeId(anneeId))
                .months(periodeRepository.findAllLeftMonthByAnneeId(anneeId))
                .build();
    }

    @Override
    public void delete(PeriodePkForm id) {
        this.periodeRepository.delete(new Periode(new PeriodePK(id.getIdAnnee(), id.getIdMois())));
    }

    @Override
    public void delete(List<PeriodePkForm> ids) {
        final List<Periode> list = ids.stream().map(item -> {
            Periode p = new Periode(new PeriodePK(item.getIdAnnee(), item.getIdMois()));
            return p;
        }).collect(Collectors.toList());

        periodeRepository.deleteAll(list);
    }
}
