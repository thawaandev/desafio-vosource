package com.thawanlc.desafio.vosource.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thawanlc.desafio.vosource.entity.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    
    boolean existsByProdutoIdAndDataHoraBetween(Long produtoId, LocalDateTime inicio, LocalDateTime fim);

    List<Movimentacao> findByProdutoIdAndDataHoraBetween(Long produtoId, LocalDateTime inicio,LocalDateTime fim);

}
