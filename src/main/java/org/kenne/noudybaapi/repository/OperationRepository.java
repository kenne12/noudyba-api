package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("select sum (p.montant) from Operation p where p.rubrique.idRubrique=:id_rubrique and p.dateOperation between :date_debut and :date_fin")
    Double findDataByIdrubric(@Param("id_rubrique") int idRubrique, @Param("date_debut") Date dateDebut, @Param("date_fin") Date dateFin);

    @Query("select  p from Operation p where  p.dateOperation between :date_debut and :date_fin")
    List<Operation> findOperationBetweenDates(@Param("date_debut") Date dateDebut, @Param("date_fin") Date dateFin);

    @Query("select o.membre.nom , o.membre.prenom , o.membre.code , sum(o.montant) from Operation o where o.dateOperation between :date_debut and :date_fin group by o.membre.nom , o.membre.prenom , o.membre.code order by sum (o.montant) desc")
    List<Object[]> findBestContributor(@Param("date_debut") Date dateDebut, @Param("date_fin") Date dateFin);


}
