package br.com.teste.eventos.utils;

import br.com.teste.eventos.dto.EventoDTO;

import java.time.LocalDate;

public class EventoDTOUtils {

    public static EventoDTO retornarEventoDTOVencido(long id) {

        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setAtivo(Boolean.TRUE);
        eventoDTO.setId(id);
        eventoDTO.setNome("Evento Teste");
        eventoDTO.setDataInicial(LocalDate.now().minusDays(1));
        eventoDTO.setDataFinal(LocalDate.now().minusDays(1));
        eventoDTO.setCodInstituicao(1L);

        return eventoDTO;
    }

    public static EventoDTO retornarEventoDTO(long id) {

        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setAtivo(Boolean.TRUE);
        eventoDTO.setId(id);
        eventoDTO.setNome("Evento Teste");
        eventoDTO.setDataInicial(LocalDate.now());
        eventoDTO.setDataFinal(LocalDate.now());
        eventoDTO.setCodInstituicao(1L);

        return eventoDTO;
    }
}
