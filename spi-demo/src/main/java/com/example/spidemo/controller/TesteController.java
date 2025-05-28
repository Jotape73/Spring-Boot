package com.example.spidemo.controller;

import com.example.spidemo.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    private final EmailService emailService;

    public TesteController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/teste")
    public String testAsync() {
        emailService.enviarEmail("jpg.marchi0701@email.com", "Olá, João!");
        return "Processando em background! Aguarde...";
    }
}
