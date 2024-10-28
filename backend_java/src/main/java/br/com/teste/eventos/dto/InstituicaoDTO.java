package br.com.teste.eventos.dto;

import br.com.teste.eventos.constants.TipoInstituicao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstituicaoDTO {

    private long id;

    @Schema(example = "Conf Florianópolis",  description = "Nome da instituição")
    private String nome;

    @Schema(example = "CONFEDERACAO",  description = "Tipo da instituição, podendo ser CONFEDERACAO, SINGULAR, CENTRAL, COOPERATIVA")
    private TipoInstituicao tipoInstituicao;

}
