package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {

    @Query("select c from Contribution c where c.membre.idMembre=:idMembre")
    List<Contribution> findContributionByIdMembre(@Param(value = "idMembre") Long idMembre);

    @Query("select c from Contribution c where c.evenement.idEvenement=:idEvenement")
    List<Contribution> findContributionByIdevenement(@Param("idEvenement") Long idEvenement);

    @Query("select c from Contribution c where c.evenement.annee.idAnnee=:idAnnee order by c.dateContribution desc , c.idContribution desc")
    List<Contribution> findAllByIdannee(@Param("idAnnee") int idAnnee);

}
