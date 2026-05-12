package com.thawanlc.desafio.vosource.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thawanlc.desafio.vosource.dto.request.ProdutoRequest;
import com.thawanlc.desafio.vosource.dto.response.ProdutoResponse;
import com.thawanlc.desafio.vosource.entity.Movimentacao;
import com.thawanlc.desafio.vosource.entity.Produto;
import com.thawanlc.desafio.vosource.entity.enums.TipoMovimento;
import com.thawanlc.desafio.vosource.mapper.ProdutoMapper;
import com.thawanlc.desafio.vosource.repository.MovimentacaoRepository;
import com.thawanlc.desafio.vosource.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired private ProdutoRepository produtoRepository;
    @Autowired private MovimentacaoRepository movimentacaoRepository;

    public ProdutoResponse criarProduto(ProdutoRequest request) {

        if(produtoRepository.existsByCodigoBarras(request.codigoBarras())) {
            throw new RuntimeException("Produto com código de barras já existe");
        }
        Produto produto = ProdutoMapper.toEntity(request);

        if(request.saldoInicial() < request.quantidadeMinima()) throw new RuntimeException("Saldo Inicial não poderá ser inferior a quantidade minima");

        produtoRepository.saveAndFlush(produto);

        if(request.saldoInicial() > 0) {
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setProduto(produto);
            movimentacao.setTipoMovimento(TipoMovimento.SALDO_INICIAL);
            movimentacao.setDataHora(LocalDateTime.now());
            movimentacao.setQuantidade(produto.getQuantidadeMinima());
            movimentacao.setDescricao("Saldo Inicial inicializado");
            movimentacao.setDocumento(TipoMovimento.SALDO_INICIAL);
            movimentacaoRepository.saveAndFlush(movimentacao);
        }

        return ProdutoMapper.toResponse(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
}
