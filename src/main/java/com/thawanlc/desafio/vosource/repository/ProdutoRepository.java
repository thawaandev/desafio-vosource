package com.thawanlc.desafio.vosource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thawanlc.desafio.vosource.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsByCodigoBarras(String codigoBarras);
    
}
