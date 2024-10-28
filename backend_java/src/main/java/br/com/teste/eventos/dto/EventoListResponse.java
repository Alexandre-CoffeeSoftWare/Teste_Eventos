package br.com.teste.eventos.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventoListResponse {

    private Long id;

    private String nome;

    private LocalDate dataInicial;

    private LocalDate dataFinal;

    private Boolean ativo;

    private Long codInstituicao;

    private String nomeInstituicao;
}
