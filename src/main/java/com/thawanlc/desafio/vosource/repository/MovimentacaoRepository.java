package com.thawanlc.desafio.vosource.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thawanlc.desafio.vosource.entity.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    
    boolean existsByProdutoIdAndDataMovimentacaoBetween(Long produtoId, LocalDateTime inicio, LocalDateTime fim);

    List<Movimentacao> findByProdutoIdAndDataMovimentacaoBetween(Long produtoId, LocalDateTime inicio,LocalDateTime fim);

}
