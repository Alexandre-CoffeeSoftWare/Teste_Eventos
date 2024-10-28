package br.com.teste.eventos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventoDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Schema(example = "Confraternização de fim de ano",  description = "Nome do evento")
    private String nome;

    @NotNull(message = "A data inicial é obrigatória")
    @Schema(description = "Data inicial do evento")
    private LocalDate dataInicial;

    @NotNull(message = "A data final é obrigatória")
    @Schema(description = "Data final do evento")
    private LocalDate dataFinal;

    @NotNull(message = "O campo ativo é obrigatorio")
    @Schema(example = "true",  description = "Se o evento está ativo ou inativo")
    private Boolean ativo;

    @NotNull(message = "A instuição é obrigatória")
    private Long codInstituicao;
}
