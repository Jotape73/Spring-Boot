package com.example.spidemo.service;

import com.example.spidemo.dto.ProdutoDTO;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProdutoService {
    private static final Logger log = LoggerFactory.getLogger(ProdutoService.class);

    @Async
    public void enviarNotificacaoAssincrona(String nome) {
        try {
            Thread.sleep(2000); // Simula delay de envio
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Erro ao enviar notificação assíncrona", e);
        }
    }

    private List<ProdutoDTO> produtos = new ArrayList<>();

    public ProdutoService() {
        produtos.add(new ProdutoDTO(1L, "Caneta"));
        produtos.add(new ProdutoDTO(2L, "Caderno"));
        produtos.add(new ProdutoDTO(3L, "Lapis"));
        produtos.add(new ProdutoDTO(4L, "Borracha"));
    }

    public ProdutoDTO buscarPorId(int id) {
    ProdutoDTO encontrado = produtos.stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .orElse(null);
        return encontrado;
    }


    public ProdutoDTO salvar(ProdutoDTO produto) {
        produto.setId((long) (produtos.size() + 1));
        produtos.add(produto);
        return produto;
    }

    public List<ProdutoDTO> listar() {
        return produtos;
    }

    public boolean deletar(int id) {
        return produtos.removeIf(p -> p.getId() == id);
    }

    public ProdutoDTO atualizar(int id, ProdutoDTO produtoDTO) {
    ProdutoDTO existente = buscarPorId(id);
    existente.setNome(produtoDTO.getNome());
    return existente;
    }
    
    public List<ProdutoDTO> buscarPorNome(String nome) {
    String nomeLower = nome.toLowerCase();
    return produtos.stream()
            .filter(p -> p.getNome().toLowerCase().startsWith(nomeLower))
            .collect(Collectors.toList());
    }


}
