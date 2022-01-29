package org.kenne.noudybaapi.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kenne.noudybaapi.service.declaration.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@WebMvcTest(MembreController.class)
public class MembreControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MembreService membreService;


    @Test
    public void test() {


    }

    private Object jsonToObject(String jsonString, Object o) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, o.getClass());
        } catch (Exception ex) {
            return null;
        }
    }


}
