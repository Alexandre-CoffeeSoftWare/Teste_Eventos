package br.com.teste.eventos.service;

import br.com.teste.eventos.constants.TipoInstituicao;
import br.com.teste.eventos.dto.EventoDTO;
import br.com.teste.eventos.dto.InstituicaoDTO;
import br.com.teste.eventos.resource.EventoEndpoint;
import br.com.teste.eventos.utils.SerealizarDataLocal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {EventoEndpoint.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(EventoEndpoint.class)
class EventoEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventoEndpoint resource;

    @MockBean
    private EventoService service;

    @MockBean
    private InstituicaoService instituicaoService;

    @Test
    @DisplayName("Teste API Evento")
    void testInserirEvento() throws Exception {

        InstituicaoDTO instituicaoRequest = new InstituicaoDTO();
        instituicaoRequest.setId(1L);
        instituicaoRequest.setNome("Instituição de Teste");
        instituicaoRequest.setTipoInstituicao(TipoInstituicao.CONFEDERACAO);

        InstituicaoDTO instituicaoResponse = new InstituicaoDTO();
        BeanUtils.copyProperties(instituicaoRequest, instituicaoResponse);

        when(instituicaoService.buscar(1L)).thenReturn(instituicaoResponse);

        EventoDTO request = new EventoDTO();
        request.setNome("Palestra de tecnologia");
        request.setDataInicial(LocalDate.now());
        request.setDataFinal(LocalDate.now().plusDays(1));
        request.setAtivo(Boolean.TRUE);
        request.setCodInstituicao(1L);

        EventoDTO response = new EventoDTO();
        BeanUtils.copyProperties(request, response);
        response.setId(1L);

        when(service.inserir(any())).thenReturn(response);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new SerealizarDataLocal());
        mapper.registerModule(module);
        String jsonRequest = mapper.writeValueAsString(request);
        String jsonResponse = mapper.writeValueAsString(response);


        this.mockMvc.perform(post("/eventos")
          .content(jsonRequest)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content().string(jsonResponse));
    }

}