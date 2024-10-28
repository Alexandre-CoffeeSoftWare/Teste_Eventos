package br.com.teste.eventos.service;

import br.com.teste.eventos.dto.EventoDTO;
import br.com.teste.eventos.dto.EventoListResponse;
import br.com.teste.eventos.entity.EventoEntity;
import br.com.teste.eventos.entity.InstituicaoEntity;
import br.com.teste.eventos.mapper.EventoMapper;
import br.com.teste.eventos.repository.EventoRepository;
import br.com.teste.eventos.repository.InstituicaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository repository;

    private final InstituicaoRepository instituicaoRepository;

    private final EventoMapper mapper;

    public EventoDTO inserir(EventoDTO body) {

        EventoEntity eventoEntity = mapper.dtoToEntity(body);

        //add exception caso n encontre a instrituicao
        InstituicaoEntity instituicaoEntity = instituicaoRepository.findById(body.getCodInstituicao()).get();

        eventoEntity.setInstituicaoEntity(instituicaoEntity);

        EventoEntity eventoEntitySaved = repository.save(eventoEntity);
        return mapper.entityToDTO(eventoEntitySaved);
    }

    public EventoDTO alterar(EventoDTO body) {

        Optional<EventoEntity> eventoEntityOptional = repository.findById(body.getId());
        if (eventoEntityOptional.isPresent()) {
            EventoEntity eventoEntity = eventoEntityOptional.get();
            eventoEntity.setNome(body.getNome());
            eventoEntity.setDataInicial(body.getDataInicial());
            eventoEntity.setDataInicial(body.getDataFinal());
            eventoEntity.setAtivo(body.getAtivo());

            repository.save(eventoEntity);
            return mapper.entityToDTO(eventoEntity);
        }
        //add exception
        return null;
    }

    public Void excluir(Long id) {
        repository.deleteById(id);
        return null;
    }

    public List<EventoListResponse> listar() {

        return mapper.entityToListDTO((List<EventoEntity>) repository.findAll());
    }

    public EventoDTO buscar(Long id) {
        // add exception
        return mapper.entityToDTO(repository.findById(id).get());
    }

    public void alterarStatus(Long id, Boolean ativo) {
        repository.updateAtivo(id, ativo);
    }

    public void inativarEventosVencidos() {

        repository.updateEventosVencidos(LocalDate.now());
    }
}
