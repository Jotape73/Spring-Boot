package com.example.spidemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AgendadorService {
    private static final Logger log = LoggerFactory.getLogger(AgendadorService.class);

    @Scheduled(fixedRate = 5000)
    public void imprimirMensagemPeriodica() {
        log.info("Executando tarefa a cada 5 segundos");
    }
}
