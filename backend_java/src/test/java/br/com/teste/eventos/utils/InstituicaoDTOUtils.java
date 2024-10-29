package br.com.teste.eventos.utils;

import br.com.teste.eventos.constants.TipoInstituicao;
import br.com.teste.eventos.dto.InstituicaoDTO;

public class InstituicaoDTOUtils {

    public static InstituicaoDTO retornarInstituicaoDTOTeste(long id) {

        InstituicaoDTO instituicaoDTO = new InstituicaoDTO();
        instituicaoDTO.setId(id);
        instituicaoDTO.setNome("Instituicao Teste");
        instituicaoDTO.setTipoInstituicao(TipoInstituicao.CENTRAL);

        return instituicaoDTO;
    }
}
