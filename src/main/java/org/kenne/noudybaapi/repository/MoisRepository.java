package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Mois;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoisRepository extends JpaRepository<Mois, Integer> {
}
