package com.example.spidemo.spi;

import com.example.spidemo.dto.ProdutoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProdutoApi {
    String BASE = "${api.produto.base}";
    String LISTAR = "${api.produto.listar}";
    String BUSCAR_POR_ID = "/{id:\\d+}";
    String DELETAR = "/{id}";
    String ATUALIZAR = "/{id}";
    String SALVAR = "/salvar";
    String BUSCAR_POR_NOME = "/buscar";

    @GetMapping(LISTAR)
    ResponseEntity<List<ProdutoDTO>> listarProdutos();

    @GetMapping(BUSCAR_POR_ID)
    ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable int id);

    @PostMapping(SALVAR)
    ResponseEntity<ProdutoDTO> salvar(@RequestBody ProdutoDTO produtoDTO);

    @DeleteMapping(DELETAR)
    ResponseEntity<Void> deletar(@PathVariable int id);

    @PutMapping(ATUALIZAR)
    ResponseEntity<ProdutoDTO> atualizar(@PathVariable int id, @RequestBody ProdutoDTO produtoDTO);

    @GetMapping(BUSCAR_POR_NOME)
    ResponseEntity<List<ProdutoDTO>> buscarPorNome(@RequestParam String nome);
}