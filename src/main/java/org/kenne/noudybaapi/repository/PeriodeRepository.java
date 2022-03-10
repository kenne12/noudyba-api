package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Annee;
import org.kenne.noudybaapi.domain.Mois;
import org.kenne.noudybaapi.domain.Periode;
import org.kenne.noudybaapi.domain.PeriodePK;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodeRepository extends JpaRepository<Periode, PeriodePK> {

    @Query("select m from Mois  m where m.id not in (select p.mois.id from Periode p where p.annee.idAnnee=:annee_id) order by m.numero")
    List<Mois> findAllLeftMonthByAnneeId(@Param("annee_id") Integer anneeId);

    List<Periode> findAllByAnnee(Annee annee, Sort sort);
}
