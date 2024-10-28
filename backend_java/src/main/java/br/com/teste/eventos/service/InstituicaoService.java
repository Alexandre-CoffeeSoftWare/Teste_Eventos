package br.com.teste.eventos.service;

import br.com.teste.eventos.dto.InstituicaoDTO;
import br.com.teste.eventos.entity.InstituicaoEntity;
import br.com.teste.eventos.mapper.InstituicaoMapper;
import br.com.teste.eventos.repository.InstituicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstituicaoService {

    private final InstituicaoRepository repository;

    private final InstituicaoMapper mapper;

    public InstituicaoDTO inserir(InstituicaoDTO body) {

        InstituicaoEntity instituicaoEntity = mapper.dtoToEntity(body);
        InstituicaoEntity instituicaoEntitySaved = repository.save(instituicaoEntity);
        return mapper.entityToDTO(instituicaoEntitySaved);
    }

    public InstituicaoDTO alterar(InstituicaoDTO body) {

        Optional<InstituicaoEntity> instituicaoEntityOptional = repository.findById(body.getId());
        if (instituicaoEntityOptional.isPresent()) {
            InstituicaoEntity instituicaoEntity = instituicaoEntityOptional.get();
            instituicaoEntity.setNome(body.getNome());
            instituicaoEntity.setTipoInstituicao(body.getTipoInstituicao());

            repository.save(instituicaoEntity);
            return mapper.entityToDTO(instituicaoEntity);
        }
        //add excpetion
        return null;
    }

    public Void excluir(Long id) {
        repository.deleteById(id);
        return null;
    }

    public List<InstituicaoDTO> listar() {

        return mapper.entityToDTO((List<InstituicaoEntity>) repository.findAll());
    }

    public InstituicaoDTO buscar(Long id) {
        return mapper.entityToDTO(repository.findById(id).get());
    }
}
