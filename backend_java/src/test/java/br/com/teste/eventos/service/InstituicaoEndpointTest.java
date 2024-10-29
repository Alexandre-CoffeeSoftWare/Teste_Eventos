package br.com.teste.eventos.service;

import br.com.teste.eventos.dto.InstituicaoDTO;
import br.com.teste.eventos.resource.InstituicaoEndpoint;
import br.com.teste.eventos.utils.InstituicaoDTOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {InstituicaoEndpoint.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(InstituicaoEndpoint.class)
class InstituicaoEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InstituicaoEndpoint resource;

    @MockBean
    private InstituicaoService service;

    @Test
    @DisplayName("Teste API Instituicao")
    void testInserirInstituicao() throws Exception {

        //objeto da request
        InstituicaoDTO request = InstituicaoDTOUtils.retornarInstituicaoDTOTeste(0);
        //objeto da resposta
        InstituicaoDTO response = InstituicaoDTOUtils.retornarInstituicaoDTOTeste(1);

        when(service.inserir(any())).thenReturn(response);

        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(request);
        String jsonResponse = mapper.writeValueAsString(response);

        this.mockMvc.perform(post("/instituicoes")
          .content(jsonRequest)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content().string(jsonResponse));
    }

}
