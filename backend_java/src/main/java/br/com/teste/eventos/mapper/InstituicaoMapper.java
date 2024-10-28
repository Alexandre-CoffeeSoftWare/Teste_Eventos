package br.com.teste.eventos.mapper;

import br.com.teste.eventos.dto.InstituicaoDTO;
import br.com.teste.eventos.entity.InstituicaoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstituicaoMapper {

    InstituicaoEntity dtoToEntity(InstituicaoDTO instituicaoDTO);

    InstituicaoDTO entityToDTO(InstituicaoEntity instituicaoEntity);

    List<InstituicaoDTO> entityToDTO(List<InstituicaoEntity> instituicaoEntities);
}
