package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Souscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SouscriptionRepository extends JpaRepository<Souscription, Long> {

    @Query("select max (s.idSouscription) from Souscription s")
    Long nexId();

    @Query("select s from Souscription s where s.membre.idMembre=:idMembre")
    List<Souscription> findSouscriptionByIdMembre(@Param("idMembre") Long idMembre);

    @Query("select s from Souscription s where s.evenement.idEvenement=:idEvenement")
    List<Souscription> findSouscriptionByIdevenement(@Param("idEvenement") Long idEvenement);

    @Query("select s from Souscription s where s.evenement.annee.idAnnee=:id_annee order by s.dateSouscription, s.idSouscription")
    List<Souscription> findAllByIdannee(@Param("id_annee") Integer idAannee);

    @Query("select s from Souscription s where s.membre.idMembre=:id_membre and s.evenement.annee.idAnnee=:id_annee and  (s.montant -  s.montantPaye) >0")
    List<Souscription> findAllByNotPayed(@Param("id_membre") long idMembre, @Param("id_annee") int idAnnee);

    @Query("select s from Souscription s where s.evenement.annee.idAnnee=:id_annee and  (s.montant -  s.montantPaye) >0")
    List<Souscription> findAllByNotPayed(@Param("id_annee") int idAnnee);
}
