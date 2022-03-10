package org.kenne.noudybaapi.service.imp;

import lombok.AllArgsConstructor;
import org.kenne.noudybaapi.domain.Annee;
import org.kenne.noudybaapi.domain.Operation;
import org.kenne.noudybaapi.domain.Periode;
import org.kenne.noudybaapi.domain.Rubrique;
import org.kenne.noudybaapi.dto.DashboardData;
import org.kenne.noudybaapi.dto.DashboardDataResponse;
import org.kenne.noudybaapi.dto.Datasets;
import org.kenne.noudybaapi.dto.OperationResponseDTO;
import org.kenne.noudybaapi.mapper.OperationMapper;
import org.kenne.noudybaapi.repository.AnneeRepository;
import org.kenne.noudybaapi.repository.OperationRepository;
import org.kenne.noudybaapi.repository.PeriodeRepository;
import org.kenne.noudybaapi.repository.RubriqueRepository;
import org.kenne.noudybaapi.service.declaration.DashboardService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DashbordServiceImpl implements DashboardService {

    private final PeriodeRepository periodeRepository;
    private final RubriqueRepository rubriqueRepository;
    private final OperationRepository operationRepository;
    private final AnneeRepository anneeRepository;

    @Override
    public DashboardDataResponse getData(int idAnnee) {

        DashboardDataResponse dashboardDataResponse = new DashboardDataResponse();

        List<Periode> periodes = periodeRepository.
                findAllByAnnee(new Annee(idAnnee), Sort.by("dateDebut").and(Sort.by("dateFin")));

        Annee annee = anneeRepository.getById(idAnnee);

        final List<Rubrique> rubriques = rubriqueRepository.findAll(Sort.by("nom"));

        final DashboardData line_data_char = new DashboardData();
        periodes.stream().forEach(periode -> {
            line_data_char.getLabels().add(periode.getMois().getCode());
        });

        rubriques.stream().forEach(rubrique -> {
            final Datasets datasets = new Datasets();
            datasets.setLabel(rubrique.getNom());
            periodes.stream().forEach(periode -> {
                Double val = operationRepository.findDataByIdrubric(rubrique.getIdRubrique(), periode.getDateDebut(), periode.getDateFin());
                datasets.getData().add(val != null ? val : 0);
            });
            line_data_char.getDatasets().add(datasets);
        });

        final DashboardData pie_data_char = new DashboardData();
        final Datasets pie_datasets = new Datasets();
        rubriques.stream().forEach(rubrique -> {
            pie_data_char.getLabels().add(rubrique.getNom());
            Double val = operationRepository.findDataByIdrubric(rubrique.getIdRubrique(), annee.getDateDebut(), annee.getDateFin());
            pie_datasets.getData().add(val != null ? val : 0);
        });
        pie_data_char.getDatasets().add(pie_datasets);


        final DashboardData bar_data_char = new DashboardData();
        final Datasets bar_datasets = new Datasets();
        periodes.stream().forEach(periode -> {
            bar_data_char.getLabels().add(periode.getShortName());
            Double val = operationRepository
                    .findOperationBetweenDates(periode.getDateDebut(), periode.getDateFin())
                    .stream().mapToDouble(Operation::getMontant).sum();
            bar_datasets.setLabel("Contributions mensuelles");
            bar_datasets.getData().add(val != null ? val : 0);
        });
        bar_data_char.getDatasets().add(bar_datasets);

        final DashboardData pie_bc_data_char = new DashboardData();
        final Datasets pie_bc_datasets = new Datasets();

        List<Object[]> objects = operationRepository.findBestContributor(annee.getDateDebut(), annee.getDateFin());

        if (!objects.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                pie_bc_data_char.getLabels().add((String) objects.get(i)[0]);
                Double val = (Double) objects.get(i)[3];
                pie_bc_datasets.getData().add(val != null ? val : 0);
            }
            dashboardDataResponse.setContributions(objects);
        }
        pie_bc_data_char.getDatasets().add(pie_bc_datasets);


        Map<String, DashboardData> map = new HashMap<>();
        map.put("line_char", line_data_char);
        map.put("pie_char", pie_data_char);
        map.put("bar_char", bar_data_char);
        map.put("pie_bc_char", pie_bc_data_char);

        dashboardDataResponse.setCharData(map);

        List<OperationResponseDTO> operation = operationRepository
                .findOperationBetweenDates(annee.getDateDebut(), annee.getDateFin()).stream().map(OperationMapper.INSTANCE::fromEntityToResponse)
                .collect(Collectors.toList());

        dashboardDataResponse.setOperations(operation);
        return dashboardDataResponse;
    }
}
