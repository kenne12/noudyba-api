package org.kenne.noudybaapi.util;

import org.kenne.noudybaapi.domain.Mois;
import org.kenne.noudybaapi.domain.Role;
import org.kenne.noudybaapi.dto.*;

import java.util.List;
import java.util.Map;


public class UtilService {

    public static Map<String, VilleResponseDTO> getData(String key, VilleResponseDTO data) {
        return DataResponse.<VilleResponseDTO>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, List<VilleResponseDTO>> getVilles(String key, List<VilleResponseDTO> data) {
        return DataResponse.<List<VilleResponseDTO>>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, MembreResponseDTO> getData(String key, MembreResponseDTO data) {
        return DataResponse.<MembreResponseDTO>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, List<MembreResponseDTO>> getData(String key, List<MembreResponseDTO> data) {
        return DataResponse.<List<MembreResponseDTO>>builder()
                .data(data)
                .build()
                .formatData(key);
    }


    public static Map<String, AnneeResponseDTO> getData(String key, AnneeResponseDTO data) {
        return DataResponse.<AnneeResponseDTO>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, List<AnneeResponseDTO>> getDatas(String key, List<AnneeResponseDTO> data) {
        return DataResponse.<List<AnneeResponseDTO>>builder()
                .data(data)
                .build()
                .formatData(key);
    }


    public static Map<String, EvenementResponseDTO> getData(String key, EvenementResponseDTO data) {
        return DataResponse.<EvenementResponseDTO>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, List<EvenementResponseDTO>> getEvents(String key, List<EvenementResponseDTO> data) {
        return DataResponse.<List<EvenementResponseDTO>>builder()
                .data(data)
                .build()
                .formatData(key);
    }


    public static Map<String, SouscriptionResponseDTO> getData(String key, SouscriptionResponseDTO data) {
        return DataResponse.<SouscriptionResponseDTO>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, List<SouscriptionResponseDTO>> getSubs(String key, List<SouscriptionResponseDTO> data) {
        return DataResponse.<List<SouscriptionResponseDTO>>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, RubriqueResponseDTO> getData(String key, RubriqueResponseDTO data) {
        return DataResponse.<RubriqueResponseDTO>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, List<RubriqueResponseDTO>> getRubrics(String key, List<RubriqueResponseDTO> data) {
        return DataResponse.<List<RubriqueResponseDTO>>builder()
                .data(data)
                .build()
                .formatData(key);
    }


    public static Map<String, PayementSouscriptionResponseDTO> getData(String key, PayementSouscriptionResponseDTO data) {
        return DataResponse.<PayementSouscriptionResponseDTO>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, List<PayementSouscriptionResponseDTO>> getPayements(String key, List<PayementSouscriptionResponseDTO> data) {
        return DataResponse.<List<PayementSouscriptionResponseDTO>>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, ContributionResponseDTO> getData(String key, ContributionResponseDTO data) {
        return DataResponse.<ContributionResponseDTO>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, List<ContributionResponseDTO>> getContributions(String key, List<ContributionResponseDTO> data) {
        return DataResponse.<List<ContributionResponseDTO>>builder()
                .data(data)
                .build()
                .formatData(key);
    }


    public static Map<String, UtilisateurResponseDTO> getData(String key, UtilisateurResponseDTO data) {
        return DataResponse.<UtilisateurResponseDTO>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, List<UtilisateurResponseDTO>> getUsers(String key, List<UtilisateurResponseDTO> data) {
        return DataResponse.<List<UtilisateurResponseDTO>>builder()
                .data(data)
                .build()
                .formatData(key);
    }


    public static Map<String, Role> getData(String key, Role data) {
        return DataResponse.<Role>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, List<Role>> getRoles(String key, List<Role> datas) {
        return DataResponse.<List<Role>>builder()
                .data(datas)
                .build()
                .formatData(key);
    }


    public static Map<String, PeriodeResponse> getData(String key, PeriodeResponse data) {
        return DataResponse.<PeriodeResponse>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, List<PeriodeResponse>> getPeriods(String key, List<PeriodeResponse> datas) {
        return DataResponse.<List<PeriodeResponse>>builder()
                .data(datas)
                .build()
                .formatData(key);
    }

    public static Map<String, List<Mois>> getMois(String key, List<Mois> datas) {
        return DataResponse.<List<Mois>>builder()
                .data(datas)
                .build()
                .formatData(key);
    }

    public static Map<String, CalendarDataReturn> getDataCalendar(String key, CalendarDataReturn data) {
        return DataResponse.<CalendarDataReturn>builder()
                .data(data)
                .build()
                .formatData(key);
    }

    public static Map<String, DashboardDataResponse> getData(String key, DashboardDataResponse data) {
        return DataResponse.<DashboardDataResponse>builder()
                .data(data)
                .build()
                .formatData(key);
    }


}
