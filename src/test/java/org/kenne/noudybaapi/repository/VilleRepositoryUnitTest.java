package org.kenne.noudybaapi.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kenne.noudybaapi.domain.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class VilleRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VilleRepository dao;

    /*@Test
    public void emptyResultTest() {
        Iterable<Ville> persons = dao.findAll();
        assertThat(persons).isEmpty();
    }*/
}
