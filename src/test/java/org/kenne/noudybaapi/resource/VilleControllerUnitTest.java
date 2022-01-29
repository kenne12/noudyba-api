package org.kenne.noudybaapi.resource;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.kenne.noudybaapi.dto.VilleResponseDTO;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


// TODO: Auto-generated Javadoc

/**
 * The Class OrganismeRestControllerIntegrationTest.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class VilleControllerUnitTest {

    /**
     * The port.
     */
    @LocalServerPort
    private int port;

    /**
     * The rest template.
     */
    TestRestTemplate restTemplate = new TestRestTemplate();

    /**
     * The headers.
     */
    HttpHeaders headers = new HttpHeaders();


    /**
     * Test add organisme.
     *
     * @throws Exception the exception
     */
    @Test
    @Order(1)
    public void testSave() throws Exception {
        headers.setContentType(MediaType.APPLICATION_JSON);

        //String json = getObjectJson(null, "USA").toString();
        //HttpEntity<String> entity = new HttpEntity<>(json, headers);
        //ResponseEntity<Response> response = restTemplate.exchange(createURLWithPort("/save"), HttpMethod.POST, entity, Response.class);

        //System.err.println(response.getBody());
        //Object o = response.getBody().getData();
        //System.err.println(o);

        //Response<VilleResponseDTO> object = (Response<VilleResponseDTO>) this.jsonToObject(response.getBody().toString(), new Response<VilleResponseDTO>());

        //assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        //assertTrue(response.getBody() != null);
        //assertTrue(object.getData().getNom().equals("USA"));
    }

    /**
     * Test edit organisme.
     *
     * @throws Exception the exception
     */
    @Test
    @Order(2)
    public void testEdit() throws Exception {
		/*headers.setContentType(MediaType.APPLICATION_JSON);
		String json = getOjectJson(6, "USA 2").toString();
		HttpEntity<String> entity = new HttpEntity<>(json, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/edit/1"), HttpMethod.PUT, entity,String.class);
		assertTrue(response.getBody().contains("USA 2"));*/
    }

    /**
     * Test find organisme.
     *
     * @throws Exception the exception
     */
    @Test
    @Order(3)
    public void testFind() throws Exception {
		/*headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/get/1"), HttpMethod.GET, entity, String.class);

		System.err.println(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertTrue(response.getBody().toString().contains("Bafoussam"));*/

    }

    /**
     * Gets the organisme json.
     *
     * @param id  the idVille
     * @param nom the nom
     * @return the Ville json
     * @throws Exception the exception
     */
    private JSONObject getObjectJson(Integer id, String nom) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idVille", id);
        jsonObject.put("nom", nom);
        return jsonObject;
    }

    /**
     * Creates the URL with port.
     *
     * @param uri the uri
     * @return the string
     */
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/api/v1/ville" + uri;
    }

    public Object jsonToObject(String jsonString, Object o) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, o.getClass());
        } catch (IOException ex) {
            return null;
        }
    }

}
