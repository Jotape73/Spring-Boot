package com.example.spidemo.controller;

import com.example.spidemo.dto.ProdutoDTO;
import com.example.spidemo.spi.ProdutoApi;
import com.example.spidemo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping(ProdutoApi.BASE)
public class ProdutoController implements ProdutoApi {
    private static final Logger log = LoggerFactory.getLogger(ProdutoController.class);

    @Autowired
    private ProdutoService service;

    @Override
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        log.info("Listando todos os produtos");
        return ResponseEntity.ok(service.listar());
    }

    @Override
    public ResponseEntity<ProdutoDTO> buscarPorId(int id) {
        log.info("Buscando produto com ID: {}", id);
        ProdutoDTO p = service.buscarPorId(id);
        if (p == null) {
            log.warn("Produto com ID {} não encontrado", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Produto com ID {} encontrado: {}", id, p.getNome());
        return ResponseEntity.ok(p);
    }

    @Override
    public ResponseEntity<List<ProdutoDTO>> buscarPorNome(String nome) {
        log.info("Iniciando busca por nome: {}", nome);
        List<ProdutoDTO> filtrados = service.buscarPorNome(nome);

        if (filtrados.isEmpty()) {
            log.warn("Nenhum produto encontrado com nome iniciando por: {}", nome);
            return ResponseEntity.notFound().build(); // 404 se não achar nada
        }

        log.info("Produtos encontrados com nome '{}': {}", nome, filtrados.size());
        service.enviarNotificacaoAssincrona(nome); // tarefa assíncrona
        return ResponseEntity.accepted().body(filtrados); // 202 Accepted com resultado
    }

    @Override
    public ResponseEntity<ProdutoDTO> salvar(ProdutoDTO produtoDTO) {
        log.info("Salvando novo produto: {}", produtoDTO.getNome());
        ProdutoDTO salvo = service.salvar(produtoDTO);
        log.info("Produto salvo com ID: {}", salvo.getId());
        return ResponseEntity.ok(salvo);
    }

    @Override
    public ResponseEntity<ProdutoDTO> atualizar(int id, ProdutoDTO produtoDTO) {
        log.info("Atualizando produto com ID: {}", id);
        ProdutoDTO atualizado = service.atualizar(id, produtoDTO);
        if (atualizado == null) {
            log.error("Erro ao atualizar: Produto com ID {} não encontrado", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Produto atualizado com sucesso: {}", atualizado);
        return ResponseEntity.ok(atualizado);
    }

    @Override
    public ResponseEntity<Void> deletar(int id) {
        log.info("Tentando deletar produto com ID: {}", id);
        boolean excluido = service.deletar(id);
         if (!excluido) {
            log.warn("Produto com ID {} não encontrado para exclusão", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Produto com ID {} deletado com sucesso", id);
        return ResponseEntity.noContent().build();
    }
}
