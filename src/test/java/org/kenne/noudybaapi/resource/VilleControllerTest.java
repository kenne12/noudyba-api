package org.kenne.noudybaapi.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kenne.noudybaapi.dto.VilleRequestDTO;
import org.kenne.noudybaapi.dto.VilleResponseDTO;
import org.kenne.noudybaapi.service.declaration.VilleService;
import org.kenne.noudybaapi.service.imp.UserDetailServiceImpl;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author kenne gervais <kennegervais@gmail.com>
 */

@Tag("VilleController")
@DisplayName("Unit testing for VilleController")
@ExtendWith(SpringExtension.class)
@WebMvcTest(VilleController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser
public class VilleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VilleService villeService;

    @MockBean
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private ObjectMapper mapper;
    private JSONObject json;

    @BeforeAll
    @AfterAll
    public void clearDataBase() {
        json = null;
    }

    @Test
    @Order(value = 1)
    @DisplayName("Find All Ville")
    public void testThatFindAllVille() throws Exception {
        List<VilleResponseDTO> list = new ArrayList<>();

        list.add(new VilleResponseDTO(1, "Bafoussam"));
        list.add(new VilleResponseDTO(2, "Douala"));
        list.add(new VilleResponseDTO(3, "Paris"));

        String result = mapper.writeValueAsString(list);
        when(villeService.findAll()).thenReturn(list);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/ville/list/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(result)))
                .andDo(print());
    }

    @Test
    @Order(value = 2)
    @DisplayName("Create new Ville")
    public void testThatWeCanCreateVille() throws Exception {
        VilleRequestDTO request = new VilleRequestDTO(null, "Bafoussam");
        VilleResponseDTO response = new VilleResponseDTO(1, "Bafoussam");

        when(villeService.save(Mockito.any(VilleRequestDTO.class))).thenReturn(response);

        String content = mapper.writeValueAsString(request);
        MvcResult result = this.mvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/ville/save")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.datas.ville.nom").value("Bafoussam"))
                .andExpect(jsonPath("$.datas.ville.idVille").exists())
                .andReturn();

        json = new JSONObject(result.getResponse().getContentAsString());
    }

    @Test
    @Order(value = 3)
    @DisplayName(value = "Find Ville By Id")
    public void testWeCanReadVille() throws Exception {

        VilleResponseDTO r = new VilleResponseDTO(1, "Bafoussam");
        String rc = mapper.writeValueAsString(r);

        when(villeService.findById(r.getIdVille())).thenReturn(r);
        this.mvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/ville/get/" + r.getIdVille()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.datas.ville.nom", is("Bafoussam")))
                .andDo(print());
    }

    @Test
    @Order(value = 4)
    @DisplayName("Edit city")
    public void testThatWeCanUpdateCity() throws Exception {

        VilleRequestDTO request = new VilleRequestDTO(1, "Bafoussam");
        VilleResponseDTO response = new VilleResponseDTO(1, "Bafoussam");

        //when(villeService.findById(Mockito.any(Integer.class))).thenReturn(response);
        when(villeService.findById(request.getIdVille())).thenReturn(response);

        String content = mapper.writeValueAsString(request);
        this.mvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/v1/ville/edit/" + request.getIdVille())
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$.datas.ville.nom", is("Bafoussam")));
    }

    @Test
    @Order(value = 5)
    @DisplayName("Delete City With Id")
    public void testThatWeCanDeleteCity() throws Exception {

        VilleResponseDTO response = new VilleResponseDTO(1, "Bafoussam");
        when(villeService.findById(response.getIdVille())).thenReturn(response);

        this.mvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/ville/delete/" + response.getIdVille()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /*
    @Test
public void updatePatientRecord_nullId() throws Exception {
    PatientRecord updatedRecord = PatientRecord.builder()
            .name("Sherlock Holmes")
            .age(40)
            .address("221B Baker Street")
            .build();

    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/patient")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(updatedRecord));

    mockMvc.perform(mockRequest)
            .andExpect(status().isBadRequest())
            .andExpect(result ->
                assertTrue(result.getResolvedException() instanceof PatientRecordController.InvalidRequestException))
    .andExpect(result ->
        assertEquals("PatientRecord or ID must not be null!", result.getResolvedException().getMessage()));
    }

@Test
public void updatePatientRecord_recordNotFound() throws Exception {
    PatientRecord updatedRecord = PatientRecord.builder()
            .patientId(5l)
            .name("Sherlock Holmes")
            .age(40)
            .address("221B Baker Street")
            .build();

    Mockito.when(patientRecordRepository.findById(updatedRecord.getPatientId())).thenReturn(null);

    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/patient")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(updatedRecord));

    mockMvc.perform(mockRequest)
            .andExpect(status().isBadRequest())
            .andExpect(result ->
                assertTrue(result.getResolvedException() instanceof NotFoundException))
    .andExpect(result ->
        assertEquals("Patient with ID 5 does not exist.", result.getResolvedException().getMessage()));
}
     */


    /*@Test
    public void deleteVilleById_notFound() throws Exception {
        Mockito.when(villeService.findById(0)).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/ville/delete/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof EntityNotFoundException))
                .andExpect(result ->
                        assertEquals("Ville not found with id : " + (-1), result.getResolvedException().getMessage()));
    }*/
}
