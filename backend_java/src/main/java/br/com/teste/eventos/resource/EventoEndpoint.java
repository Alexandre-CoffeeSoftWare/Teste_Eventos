package br.com.teste.eventos.resource;

import br.com.teste.eventos.dto.EventoDTO;
import br.com.teste.eventos.dto.EventoListResponse;
import br.com.teste.eventos.resource.api.EventoAPI;
import br.com.teste.eventos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventoEndpoint implements EventoAPI {

    @Autowired
    EventoService service;

    @Override
    public EventoDTO inserir(EventoDTO body) {
        return service.inserir(body);
    }

    @Override
    public EventoDTO alterar(EventoDTO body) {
        return service.alterar(body);
    }

    @Override
    public Void excluir(Long id) {
        return service.excluir(id);
    }

    @Override
    public List<EventoListResponse> listar() {
        return service.listar();
    }

    @Override
    public EventoDTO buscar(Long id) {
        return service.buscar(id);
    }

    @Override
    public void alterarStatus(Long id, Boolean ativo) {
        service.alterarStatus(id, ativo);
    }
}
