package com.example.spidemo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Async
    public void enviarEmail(String destino, String conteudo) {
        System.out.println("Enviando e-mail para: " + destino);
        try {
            Thread.sleep(3000); // simula demora
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("E-mail enviado!");
    }
}
