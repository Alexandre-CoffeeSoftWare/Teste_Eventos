package br.com.teste.eventos.resource;

import br.com.teste.eventos.dto.InstituicaoDTO;
import br.com.teste.eventos.resource.api.InstituicaoAPI;
import br.com.teste.eventos.service.InstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstituicaoEndpoint implements InstituicaoAPI {

    @Autowired
    private InstituicaoService service;


    @Override
    public InstituicaoDTO inserir(InstituicaoDTO body) {
        return service.inserir(body);
    }

    @Override
    public InstituicaoDTO alterar(InstituicaoDTO body) {
        return service.alterar(body);
    }

    @Override
    public Void excluir(Long id) {
        return service.excluir(id);
    }

    @Override
    public List<InstituicaoDTO> listar() {
        return service.listar();
    }

    @Override
    public InstituicaoDTO buscar(Long id) {
        return service.buscar(id);
    }
}
