package org.kenne.noudybaapi.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kenne.noudybaapi.domain.Ville;
import org.kenne.noudybaapi.repository.VilleRepository;
import org.kenne.noudybaapi.service.declaration.VilleService;
import org.kenne.noudybaapi.service.imp.VilleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class VilleServiceImpUnitTest {

    @MockBean
    private VilleRepository dao;

    @TestConfiguration
    static class VilleServiceConfig {
        @Bean
        public VilleService villeService() {
            return new VilleServiceImpl();
        }
    }

    @Autowired
    private VilleService service;

    @Test
    public void emptyResultTest() {
        Iterable<Ville> persons = dao.findAll();
        assertThat(persons).isEmpty();
    }

}
