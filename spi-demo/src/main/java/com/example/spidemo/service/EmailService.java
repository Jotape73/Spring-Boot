package com.example.spidemo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    // Simula verificação simples de e-mail
    public boolean emailExiste(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    @Async
    public void enviarEmail(String destino, String mensagem) {
        try {
            Thread.sleep(3000);
            System.out.println("E-mail enviado para " + destino + " com a mensagem: " + mensagem);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Erro ao enviar e-mail");
        }
    }
}
