package br.com.teste.eventos.mapper;

import br.com.teste.eventos.dto.EventoDTO;
import br.com.teste.eventos.dto.EventoListResponse;
import br.com.teste.eventos.entity.EventoEntity;
import br.com.teste.eventos.entity.InstituicaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    @Mapping(target = "instituicaoEntity.id", source = "codInstituicao")
    EventoEntity dtoToEntity(EventoDTO eventoDTO);

    @Mapping(target = "codInstituicao", source = "instituicaoEntity.id")
    EventoDTO entityToDTO(EventoEntity eventoEntity);

    List<EventoDTO> entityToDTO(List<EventoEntity> eventoEntities);

    @Mapping(target = "codInstituicao", source = "instituicaoEntity.id")
    @Mapping(target = "nomeInstituicao", source = "instituicaoEntity.nome")
    EventoListResponse entityToListDTO(EventoEntity eventoEntity);

    List<EventoListResponse> entityToListDTO(List<EventoEntity> eventoEntities);

    InstituicaoEntity map(Long value);
}
