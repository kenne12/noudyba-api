package org.kenne.noudybaapi.service.imp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kenne.noudybaapi.domain.Poste;
import org.kenne.noudybaapi.dto.PosteRequestDTO;
import org.kenne.noudybaapi.dto.PosteResponseDTO;
import org.kenne.noudybaapi.exception.EntityNotFoundException;
import org.kenne.noudybaapi.mapper.PosteMapper;
import org.kenne.noudybaapi.repository.PosteRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PosteServiceImplTest {

    @Mock
    private PosteRepository posteRepository;

    private PosteServiceImpl underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new PosteServiceImpl(posteRepository);
    }

    @Test
    @Order(value = 1)
    void canFindAllPoste() {

        // Given
        List<Poste> list = new ArrayList<>();
        list.add(new Poste(1, "P1", "POSTE_1"));
        list.add(new Poste(2, "P2", "POSTE_2"));
        list.add(new Poste(3, "P3", "POSTE_3"));
        given(posteRepository.findAll()).willReturn(list);

        // when
        List<PosteResponseDTO> actual = underTest.findAll();

        //then
        int expected = 3;
        assertThat(actual).hasSize(expected);
    }

    @Test
    @Order(value = 2)
    public void canGetPosteById() {

        // Given // Arrange
        Poste poste = new Poste(1, "P1", "POSTE_P1");
        //when(posteRepository.findById(poste.getIdPoste())).thenReturn(Optional.of(poste));
        given(posteRepository.findById(poste.getIdPoste())).willReturn(Optional.of(poste));

        // when // Act
        PosteResponseDTO p = underTest.findById(poste.getIdPoste());

        // Then // Assert
        assertThat(p).isNotNull();
        verify(posteRepository, times(1)).findById(poste.getIdPoste());
        assertThat(p.getNom()).isEqualTo(poste.getNom());
    }

    @Test
    @Order(value = 3)
    public void canSavePoste() {
        // given
        Poste poste = new Poste(null, "P1", "POSTE_P1");
        given(posteRepository.save(poste)).willReturn(poste);

        // when
        PosteResponseDTO expected =
                underTest.save(PosteMapper.INSTANCE.fromEntityToRequest(poste));

        // then // assert

        assertThat(expected).isNotNull();
        verify(posteRepository, times(1)).save(poste);
    }


    @Test
    @Order(value = 4)
    public void canDeletePosteIfFound() {
        // given // Arrange
        Poste poste = new Poste(1, "P1", "POSTE_P1");
        when(posteRepository.findById(poste.getIdPoste())).thenReturn(Optional.of(poste));

        // when //Act
        underTest.delete(poste.getIdPoste());

        // then // assert
        verify(posteRepository, times(1))
                .deleteById(poste.getIdPoste());
    }


    @Test
    @Order(value = 5)
    public void findByIdShoudThrowExceptionIfPostNotFound() {
        //given
        Poste poste = new Poste(0, "P1", "POSTE_1");

        given(posteRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            underTest.delete(poste.getIdPoste());
        });
    }

    @Test
    @Order(value = 6)
    public void shouldUpdatePosteIfFound() {
        Poste poste = new Poste(1, "P1", "POSTE");

        PosteRequestDTO posteRequestDTO = new PosteRequestDTO(poste.getIdPoste(), "P2", "POSTE_2");

        given(posteRepository.findById(poste.getIdPoste())).willReturn(Optional.of(poste));

        underTest.edit(posteRequestDTO);

        verify(posteRepository).save(new Poste(1, "P2", "POSTE_2"));

        verify(posteRepository).findById(poste.getIdPoste());
    }

    @Test
    @Order(value = 7)
    public void updateShouldThrowExceptionIfPostNotFound() {
        Poste poste = new Poste(0, "P1", "POSTE");
        given(posteRepository.findById(anyInt())).willReturn(Optional.ofNullable(null));

        PosteRequestDTO p = new PosteRequestDTO(poste.getIdPoste(), "P2", "POSTE2");
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            underTest.edit(p);
        });
    }
}
