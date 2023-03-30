package br.com.stapassoli.consumirviacepapi;

import br.com.stapassoli.consumirviacepapi.model.CEP;
import br.com.stapassoli.consumirviacepapi.model.LogradouroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ConsumirViaCepApiApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void deveBuscarDadosCepFrete() throws Exception {

        CEP cep = CEP.builder().cep("88745000").build();
        var jsonBody = mapper.writeValueAsString(cep);
        URI uri = new URI("/buscarCEP");

        String response = mockMvc.perform(
                    get(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBody)
                )
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andReturn().getResponse().getContentAsString();

        LogradouroDTO logradouroDTO = mapper.readValue(response, LogradouroDTO.class);

        assertEquals(logradouroDTO.getFrete(),17.3);

    }

}
