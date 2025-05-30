package com.example.spidemo.controller;

import com.example.spidemo.dto.ProdutoDTO;
import com.example.spidemo.spi.ProdutoApi;
import com.example.spidemo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ProdutoApi.BASE)
public class ProdutoController implements ProdutoApi {

    @Autowired
    private ProdutoService service;

    @Override
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        return ResponseEntity.ok(service.listar());
    }

    @Override
    public ResponseEntity<ProdutoDTO> buscarPorId(int id) {
        ProdutoDTO p = service.buscarPorId(id);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @Override
    public ResponseEntity<List<ProdutoDTO>> buscarPorNome(String nome) {
        List<ProdutoDTO> filtrados = service.buscarPorNome(nome);

        if (filtrados.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 se não achar nada
        }

        service.enviarNotificacaoAssincrona(nome); // tarefa assíncrona
        return ResponseEntity.accepted().body(filtrados); // 202 Accepted com resultado
    }

    @Override
    public ResponseEntity<ProdutoDTO> salvar(ProdutoDTO produtoDTO) {
        ProdutoDTO salvo = service.salvar(produtoDTO);
        return ResponseEntity.ok(salvo);
    }

    @Override
    public ResponseEntity<ProdutoDTO> atualizar(int id, ProdutoDTO produtoDTO) {
        ProdutoDTO atualizado = service.atualizar(id, produtoDTO);
        if (atualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(atualizado);
    }

    @Override
    public ResponseEntity<Void> deletar(int id) {
        boolean excluido = service.deletar(id);
        if (!excluido) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
