package org.kenne.noudybaapi.service.imp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kenne.noudybaapi.repository.VilleRepository;
import org.kenne.noudybaapi.service.declaration.VilleService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class VilleServiceImpTest {

    @Mock
    private VilleRepository villeRepository;


    private VilleService villeService;

    @BeforeEach
    void setUp() {
        villeService = new VilleServiceImpl(villeRepository);
    }


    @Test
    public void canFindAll() {
        // when
        villeService.findAll();

        // then
        Mockito.verify(villeRepository).findAll();
    }
}
