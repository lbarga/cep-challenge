package br.com.buscar_cep.controllers;

import br.com.buscar_cep.models.CepDTO;
import br.com.buscar_cep.services.CepService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(CepController.class)
public class CepControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CepService cepService;

    @Test
    public void testGetCep() throws Exception {
        String cep = "01001-000";
        CepDTO expectedCepDTO = createExpectedCepDTO();

        when(cepService.getCepInMultiplesApis(anyString())).thenReturn(expectedCepDTO);

        MvcResult mvcResult = mockMvc.perform(get("/cep/{cep}", cep))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();

        CepDTO responseCepDTO = objectMapper.readValue(jsonResponse, CepDTO.class);

        assertEquals(responseCepDTO, expectedCepDTO);
    }

    private CepDTO createExpectedCepDTO() {
        CepDTO cepDTO = new CepDTO();
        cepDTO.setCep("01001-000");
        cepDTO.setLogradouro("Praca da Se - lado impar");
        cepDTO.setBairro("Se");
        cepDTO.setCidade("Sao Paulo");
        cepDTO.setEstado("SP");
        cepDTO.setPais("Brasil");
        cepDTO.setCodigoIBGECidade(null);
        cepDTO.setCodigoIBGEEstado(null);
        return cepDTO;
    }
}
