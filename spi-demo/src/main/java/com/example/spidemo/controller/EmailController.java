package com.example.spidemo.controller;

import com.example.spidemo.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/emails")
public class EmailController {
private static final Logger log = LoggerFactory.getLogger(EmailController.class);


    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarEmail(@RequestParam String email) {
        if (!emailService.emailExiste(email)) {
            log.warn("E-mail nao encontrado ou invalido: {}", email);
            return ResponseEntity.notFound().build();
        }

        // Dispara a tarefa assíncrona
       emailService.enviarEmail(email, "Mensagem automática via sistema");
        log.info("E-mail enviado com sucesso para: {}", email);

        // Retorna 202 Accepted porque o envio será feito em background
        return ResponseEntity.accepted().body("E-mail será enviado em background para: " + email);
    }
}
