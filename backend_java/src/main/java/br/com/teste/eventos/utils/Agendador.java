package br.com.teste.eventos.utils;

import br.com.teste.eventos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Agendador {

    @Autowired
    private EventoService eventoService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void buscarEventosVencidos() {

        System.out.println("Buscando eventos vencidos para inativar" + dateFormat.format(new Date()));
        eventoService.inativarEventosVencidos();
    }
}
