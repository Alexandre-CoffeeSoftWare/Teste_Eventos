package br.com.teste.eventos.mapper;

import br.com.teste.eventos.dto.EventoDTO;
import br.com.teste.eventos.dto.EventoListResponse;
import br.com.teste.eventos.entity.EventoEntity;
import br.com.teste.eventos.entity.InstituicaoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-29T16:58:01-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
@Component
public class EventoMapperImpl implements EventoMapper {

    @Override
    public EventoEntity dtoToEntity(EventoDTO eventoDTO) {
        if ( eventoDTO == null ) {
            return null;
        }

        EventoEntity eventoEntity = new EventoEntity();

        eventoEntity.setInstituicaoEntity( eventoDTOToInstituicaoEntity( eventoDTO ) );
        if ( eventoDTO.getId() != null ) {
            eventoEntity.setId( eventoDTO.getId() );
        }
        eventoEntity.setNome( eventoDTO.getNome() );
        eventoEntity.setDataInicial( eventoDTO.getDataInicial() );
        eventoEntity.setDataFinal( eventoDTO.getDataFinal() );
        eventoEntity.setAtivo( eventoDTO.getAtivo() );

        return eventoEntity;
    }

    @Override
    public EventoDTO entityToDTO(EventoEntity eventoEntity) {
        if ( eventoEntity == null ) {
            return null;
        }

        EventoDTO eventoDTO = new EventoDTO();

        eventoDTO.setCodInstituicao( eventoEntityInstituicaoEntityId( eventoEntity ) );
        eventoDTO.setId( eventoEntity.getId() );
        eventoDTO.setNome( eventoEntity.getNome() );
        eventoDTO.setDataInicial( eventoEntity.getDataInicial() );
        eventoDTO.setDataFinal( eventoEntity.getDataFinal() );
        eventoDTO.setAtivo( eventoEntity.getAtivo() );

        return eventoDTO;
    }

    @Override
    public List<EventoDTO> entityToDTO(List<EventoEntity> eventoEntities) {
        if ( eventoEntities == null ) {
            return null;
        }

        List<EventoDTO> list = new ArrayList<EventoDTO>( eventoEntities.size() );
        for ( EventoEntity eventoEntity : eventoEntities ) {
            list.add( entityToDTO( eventoEntity ) );
        }

        return list;
    }

    @Override
    public EventoListResponse entityToListDTO(EventoEntity eventoEntity) {
        if ( eventoEntity == null ) {
            return null;
        }

        EventoListResponse eventoListResponse = new EventoListResponse();

        eventoListResponse.setCodInstituicao( eventoEntityInstituicaoEntityId( eventoEntity ) );
        eventoListResponse.setNomeInstituicao( eventoEntityInstituicaoEntityNome( eventoEntity ) );
        eventoListResponse.setId( eventoEntity.getId() );
        eventoListResponse.setNome( eventoEntity.getNome() );
        eventoListResponse.setDataInicial( eventoEntity.getDataInicial() );
        eventoListResponse.setDataFinal( eventoEntity.getDataFinal() );
        eventoListResponse.setAtivo( eventoEntity.getAtivo() );

        return eventoListResponse;
    }

    @Override
    public List<EventoListResponse> entityToListDTO(List<EventoEntity> eventoEntities) {
        if ( eventoEntities == null ) {
            return null;
        }

        List<EventoListResponse> list = new ArrayList<EventoListResponse>( eventoEntities.size() );
        for ( EventoEntity eventoEntity : eventoEntities ) {
            list.add( entityToListDTO( eventoEntity ) );
        }

        return list;
    }

    @Override
    public InstituicaoEntity map(Long value) {
        if ( value == null ) {
            return null;
        }

        InstituicaoEntity instituicaoEntity = new InstituicaoEntity();

        return instituicaoEntity;
    }

    protected InstituicaoEntity eventoDTOToInstituicaoEntity(EventoDTO eventoDTO) {
        if ( eventoDTO == null ) {
            return null;
        }

        InstituicaoEntity instituicaoEntity = new InstituicaoEntity();

        if ( eventoDTO.getCodInstituicao() != null ) {
            instituicaoEntity.setId( eventoDTO.getCodInstituicao() );
        }

        return instituicaoEntity;
    }

    private Long eventoEntityInstituicaoEntityId(EventoEntity eventoEntity) {
        if ( eventoEntity == null ) {
            return null;
        }
        InstituicaoEntity instituicaoEntity = eventoEntity.getInstituicaoEntity();
        if ( instituicaoEntity == null ) {
            return null;
        }
        long id = instituicaoEntity.getId();
        return id;
    }

    private String eventoEntityInstituicaoEntityNome(EventoEntity eventoEntity) {
        if ( eventoEntity == null ) {
            return null;
        }
        InstituicaoEntity instituicaoEntity = eventoEntity.getInstituicaoEntity();
        if ( instituicaoEntity == null ) {
            return null;
        }
        String nome = instituicaoEntity.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }
}
