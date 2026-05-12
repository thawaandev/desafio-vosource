package com.thawanlc.desafio.vosource.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thawanlc.desafio.vosource.dto.request.MovimentacaoRequest;
import com.thawanlc.desafio.vosource.dto.response.MovimentacaoResponse;
import com.thawanlc.desafio.vosource.entity.Movimentacao;
import com.thawanlc.desafio.vosource.entity.Produto;
import com.thawanlc.desafio.vosource.entity.enums.TipoMovimento;
import com.thawanlc.desafio.vosource.exceptions.RecursoNaoEncontradoException;
import com.thawanlc.desafio.vosource.mapper.MovimentacaoMapper;
import com.thawanlc.desafio.vosource.repository.MovimentacaoRepository;
import com.thawanlc.desafio.vosource.repository.ProdutoRepository;

@Service
public class MovimentacaoService {
    
    @Autowired private MovimentacaoRepository movimentacaoRepository;
    @Autowired private ProdutoRepository produtoRepository;

    public MovimentacaoResponse lancarMovimentacao(MovimentacaoRequest request) {
        Produto produto = produtoRepository.findById(request.produtoId())
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Movimentacao movimentacao = MovimentacaoMapper.toEntity(request);

        if(request.tipoMovimento() == TipoMovimento.ENTRADA) {
            produto.setQuantidadeMinima(produto.getQuantidadeMinima() + request.quantidade());
            movimentacao.setDocumento(TipoMovimento.ENTRADA);
        } else if(request.tipoMovimento() == TipoMovimento.SAIDA) {
            if(produto.getQuantidadeMinima() < request.quantidade()) {
                throw new RuntimeException("Estoque insuficiente");
            }
            produto.setQuantidadeMinima(produto.getQuantidadeMinima() - request.quantidade());
            movimentacao.setDocumento(TipoMovimento.SAIDA);
        }

        
        movimentacao.setProduto(produto);
        movimentacaoRepository.saveAndFlush(movimentacao);
        produtoRepository.saveAndFlush(produto);
        return MovimentacaoMapper.toResponse(movimentacao);
    }

    public List<MovimentacaoResponse> buscarTodos() {
        return movimentacaoRepository.findAll().stream().map(MovimentacaoMapper::toResponse).toList();
    }

    public MovimentacaoResponse registrarAjuste(Long id, boolean isEntrada, MovimentacaoRequest update) {
        Movimentacao movimentacao = movimentacaoRepository.findById(id).orElseThrow(
            () -> new RecursoNaoEncontradoException("Movimentacao " + id + " não encontrado"));

        TipoMovimento tipo = isEntrada ? TipoMovimento.ENTRADA : TipoMovimento.SAIDA;

        if(movimentacao.getDocumento() != TipoMovimento.ENTRADA && movimentacao.getDocumento() != TipoMovimento.SAIDA) {
            throw new RecursoNaoEncontradoException("Somente uma alteraçao para cada movimentação");
        }
        
        MovimentacaoMapper.merge(movimentacao, update);
        movimentacao.setDocumento(tipo);
        movimentacaoRepository.save(movimentacao);
        return MovimentacaoMapper.toResponse(movimentacao);
    }


    public List<Movimentacao> buscarPorProdutoEPeriodo(Long produtoId, LocalDateTime inicio, LocalDateTime fim) {
        return movimentacaoRepository.findByProdutoIdAndDataHoraBetween(produtoId, inicio, fim);
    }

    public List<Movimentacao> filtrarPorTipoMovimentacao(TipoMovimento tipoMovimento) {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findAll();

        if(tipoMovimento == null) {
            return movimentacoes;
        }

        return movimentacoes.stream()
            .filter(m -> m.getTipoMovimento() == tipoMovimento)
            .toList();

    }

}
