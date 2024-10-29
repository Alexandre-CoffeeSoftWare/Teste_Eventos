package br.com.teste.eventos.service;

import br.com.teste.eventos.dto.EventoDTO;
import br.com.teste.eventos.dto.InstituicaoDTO;
import br.com.teste.eventos.resource.EventoEndpoint;
import br.com.teste.eventos.utils.EventoDTOUtils;
import br.com.teste.eventos.utils.InstituicaoDTOUtils;
import br.com.teste.eventos.utils.SerealizarDataLocal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
    private EventoService eventoService;

    @MockBean
    private InstituicaoService instituicaoService;

    @Test
    @Order(1)
    @DisplayName("Teste API Evento")
    void testInserirEvento() throws Exception {

        InstituicaoDTO instituicaoDTO = InstituicaoDTOUtils.retornarInstituicaoDTOTeste(0);
        instituicaoService.inserir(instituicaoDTO);

        EventoDTO request = EventoDTOUtils.retornarEventoDTO(0);
        EventoDTO response = EventoDTOUtils.retornarEventoDTO(1);
        when(eventoService.inserir(any())).thenReturn(response);

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

    @Test
    @Order(2)
    @DisplayName("Teste API Evento Vencido")
    void testInserirEventoVencido() throws Exception {

        InstituicaoDTO instituicaoDTO = InstituicaoDTOUtils.retornarInstituicaoDTOTeste(0);
        when(this.instituicaoService.inserir(any())).thenReturn(instituicaoDTO);

        instituicaoService.inserir(instituicaoDTO);

        EventoDTO request = EventoDTOUtils.retornarEventoDTOVencido(0);
        EventoDTO response = EventoDTOUtils.retornarEventoDTOVencido(1);
        when(eventoService.inserir(any())).thenReturn(response);

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

        verify(instituicaoService).inserir(instituicaoDTO);
    }

}