package com.thawanlc.desafio.vosource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thawanlc.desafio.vosource.dto.request.ProdutoRequest;
import com.thawanlc.desafio.vosource.dto.response.ProdutoResponse;
import com.thawanlc.desafio.vosource.entity.Produto;
import com.thawanlc.desafio.vosource.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired private ProdutoService produtoService;
    
    @PostMapping("/criar")
    public ResponseEntity<?> salvarProduto(@RequestBody ProdutoRequest request) {
        ProdutoResponse produto = produtoService.criarProduto(request);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarTodos();
    }

}
