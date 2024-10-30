package com.projeto.sistema1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.projeto.sistema1.modelos.Contrato;
import com.projeto.sistema1.repositorios.ContratoRepositorio;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepositorio contratoRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 0 * * ?") // Executa diariamente à meia-noite (Quando for a testar por exemplo para mandar mensagem ao parceiro as 11h e 8min e 0seg seria @Scheduled(cron = "0 8 11 * * ?"))
    public void verificarContratosExpirados() {
        LocalDate hoje = LocalDate.now();
        List<Contrato> contratosExpirados = contratoRepository.findByDataExpiracao(hoje);
        for (Contrato contrato : contratosExpirados) {
            String email = contrato.getParceiro().getEmail();
            String subject = "Contrato Expirado";
            String text = "Seu contrato com o projeto " + contrato.getProjecto().getNome() + " expirou.";
            emailService.sendEmail(email, subject, text);
        }
    }
}