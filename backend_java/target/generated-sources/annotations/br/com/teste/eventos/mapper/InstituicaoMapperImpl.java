package br.com.teste.eventos.mapper;

import br.com.teste.eventos.dto.InstituicaoDTO;
import br.com.teste.eventos.entity.InstituicaoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-29T16:58:00-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class InstituicaoMapperImpl implements InstituicaoMapper {

    @Override
    public InstituicaoEntity dtoToEntity(InstituicaoDTO instituicaoDTO) {
        if ( instituicaoDTO == null ) {
            return null;
        }

        InstituicaoEntity instituicaoEntity = new InstituicaoEntity();

        instituicaoEntity.setId( instituicaoDTO.getId() );
        instituicaoEntity.setNome( instituicaoDTO.getNome() );
        instituicaoEntity.setTipoInstituicao( instituicaoDTO.getTipoInstituicao() );

        return instituicaoEntity;
    }

    @Override
    public InstituicaoDTO entityToDTO(InstituicaoEntity instituicaoEntity) {
        if ( instituicaoEntity == null ) {
            return null;
        }

        InstituicaoDTO instituicaoDTO = new InstituicaoDTO();

        instituicaoDTO.setId( instituicaoEntity.getId() );
        instituicaoDTO.setNome( instituicaoEntity.getNome() );
        instituicaoDTO.setTipoInstituicao( instituicaoEntity.getTipoInstituicao() );

        return instituicaoDTO;
    }

    @Override
    public List<InstituicaoDTO> entityToDTO(List<InstituicaoEntity> instituicaoEntities) {
        if ( instituicaoEntities == null ) {
            return null;
        }

        List<InstituicaoDTO> list = new ArrayList<InstituicaoDTO>( instituicaoEntities.size() );
        for ( InstituicaoEntity instituicaoEntity : instituicaoEntities ) {
            list.add( entityToDTO( instituicaoEntity ) );
        }

        return list;
    }
}
