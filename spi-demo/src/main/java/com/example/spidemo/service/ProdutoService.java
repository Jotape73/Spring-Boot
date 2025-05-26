package com.example.spidemo.service;

import com.example.spidemo.dto.ProdutoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private List<ProdutoDTO> produtos = new ArrayList<>();

    public ProdutoService() {
        produtos.add(new ProdutoDTO(1L, "Caneta"));
        produtos.add(new ProdutoDTO(2L, "Caderno"));
        produtos.add(new ProdutoDTO(3L, "Lapis"));
        produtos.add(new ProdutoDTO(4L, "Borracha"));
    }

    public List<ProdutoDTO> listar() {
        return produtos;
    }

    public ProdutoDTO buscarPorId(int id) {
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public ProdutoDTO salvar(ProdutoDTO produtoDTO) {
        long novoId = produtos.size() + 1L;
        produtoDTO.setId(novoId);
        produtos.add(produtoDTO);
        return produtoDTO;
    }

    public boolean deletar(int id) {
        return produtos.removeIf(p -> p.getId() == id);
    }

    public ProdutoDTO atualizar(int id, ProdutoDTO produtoDTO) {
    ProdutoDTO existente = buscarPorId(id);
    if (existente == null) {
        return null; // não encontrado
    }
    existente.setNome(produtoDTO.getNome()); // atualiza o nome (pode adicionar mais campos depois)
    return existente;
    }
    
    public List<ProdutoDTO> buscarPorNome(String nome) {
    String nomeLower = nome.toLowerCase();
    return produtos.stream()
            .filter(p -> p.getNome().toLowerCase().startsWith(nomeLower))
            .collect(Collectors.toList());
    }


}
