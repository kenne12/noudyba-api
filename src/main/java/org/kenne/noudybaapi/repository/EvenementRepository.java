package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Annee;
import org.kenne.noudybaapi.domain.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {

    @Query("select max (e.idEvenement) from Evenement e")
    Long nextId();

    @Query("select e from Evenement e where e.rubrique.idRubrique=:idRubrique")
    List<Evenement> findAllByIdrubrique(@Param(value = "idRubrique") Integer idRubrique);

    @Query("select e from Evenement e where e.annee.idAnnee=:idAnnee")
    List<Evenement> findAllByIdannee(@Param(value = "idAnnee") Integer idAnnee);


    List<Evenement> findAllByAnneeOrderByIdEvenement(Annee annee);
}
