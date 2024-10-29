package br.com.teste.eventos.service;

import br.com.teste.eventos.constants.TipoInstituicao;
import br.com.teste.eventos.entity.EventoEntity;
import br.com.teste.eventos.entity.InstituicaoEntity;
import br.com.teste.eventos.repository.EventoRepository;
import br.com.teste.eventos.repository.InstituicaoRepository;
import br.com.teste.eventos.utils.Agendador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public class AgendadorTest {

    @Autowired
    private Agendador agendador;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Test
    public void testInativarEventoVencido() {

        InstituicaoEntity instituicao = new InstituicaoEntity();
        instituicao.setNome("Instituição Teste");
        instituicao.setTipoInstituicao(TipoInstituicao.CONFEDERACAO);
        instituicao = instituicaoRepository.save(instituicao);

        EventoEntity eventoVencido = new EventoEntity();
        eventoVencido.setNome("Evento Vencido");
        eventoVencido.setDataInicial(LocalDate.now().minusDays(5));
        eventoVencido.setDataFinal(LocalDate.now().minusDays(1));
        eventoVencido.setAtivo(true);
        eventoVencido.setInstituicaoEntity(instituicao);
        eventoVencido = eventoRepository.save(eventoVencido);

        agendador.buscarEventosVencidos();

        EventoEntity eventoAtualizado = eventoRepository.findById(eventoVencido.getId()).orElse(null);
        assertThat(eventoAtualizado).isNotNull();
        assertThat(eventoAtualizado.getAtivo()).isFalse();
    }
}

