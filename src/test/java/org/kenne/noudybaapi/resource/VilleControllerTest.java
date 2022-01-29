package org.kenne.noudybaapi.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.kenne.noudybaapi.dto.VilleRequestDTO;
import org.kenne.noudybaapi.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author kenne gervais <kennegervais@gmail.com>
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@Tag("VilleController")
@DisplayName("Unit testing for VilleController")
public class VilleControllerTest {

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private MockMvc mvc;

    private JSONObject json;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    @AfterAll
    public void clearDataBase() {
        villeRepository.deleteAll();
        json = null;
    }

    @Test
    @Order(value = 1)
    @DisplayName("Create a City")
    public void testThatWeCanCreateVille() throws Exception {
        VilleRequestDTO requestDTO = new VilleRequestDTO(null, "Bafoussam");
        String content = mapper.writeValueAsString(requestDTO);
        MvcResult result = this.mvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/v1/ville/save")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("City saved saved successfully"))
                .andExpect(jsonPath("$.data.nom").value("Bafoussam"))
                .andExpect(jsonPath("$.data.idVille").exists())
                .andExpect(jsonPath("$.statusCode", is(201)))
                .andReturn();

        json = new JSONObject(result.getResponse().getContentAsString());
    }

    @Test
    @Order(value = 2)
    @DisplayName(value = "Find Ville By Id")
    public void testWeCanReadStudent() throws Exception {
        this.mvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/ville/get/" + json.getJSONObject("data").getInt("idVille")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("City fetched with id " + json.getJSONObject("data").getInt("idVille")))
                .andExpect(jsonPath("$.data.nom", is("Bafoussam")));
        //.andExpect(jsonPath("$.message", is("City fetched with id " + json.getJSONObject("data").getInt("idVille"))));
    }

    @Test
    @Order(value = 3)
    @DisplayName("Show list of cities")
    public void testThatWeCanShowListOfStudent() throws Exception {
        this.mvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/v1/ville/list/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].nom", is("Bafoussam")))
                .andExpect(jsonPath("$.message").value("Cities list fetch successfully"));

    }

    @Test
    @Order(value = 4)
    @DisplayName("Edit city With Id")
    public void testThatWeCanUpdateCity() throws Exception {
        VilleRequestDTO requestDTO = new VilleRequestDTO(json.getJSONObject("data").getInt("idVille"), "Douala");
        String content = mapper.writeValueAsString(requestDTO);
        this.mvc.perform(
                        MockMvcRequestBuilders
                                .put("/api/v1/ville/edit/" + json.getJSONObject("data").getInt("idVille"))
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nom", is("Douala")))
                .andExpect(jsonPath("$.message", is("City edited successfully")))
                .andExpect(jsonPath("$.data.idVille", is(json.getJSONObject("data").getInt("idVille"))));
    }

    @Test
    @Order(value = 5)
    @DisplayName("Delete City With Id")
    public void testThatWeCanDeleteCity() throws Exception {
        this.mvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/ville/delete/" + json.getJSONObject("data").getInt("idVille")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("City deleted successfully")));

    }

    @Test
    @Order(value = 6)
    @DisplayName("Wrong studend Id")
    public void testThatWeCanNotFindStudent() throws Exception {
        this.mvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/ville/get/" + json.getJSONObject("data").getInt("idVille")))
                .andDo(print())
                .andExpect(status().isNotFound())
                //.andExpect(jsonPath("$.statusCode" , is(404)))
                .andExpect(jsonPath("$.httpStatus" , is("NOT_FOUND")))
                .andExpect(jsonPath("$.message" , is("Entity not found" )));

    }
}
