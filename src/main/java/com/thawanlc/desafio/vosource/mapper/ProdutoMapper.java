package com.thawanlc.desafio.vosource.mapper;

import org.springframework.stereotype.Component;

import com.thawanlc.desafio.vosource.dto.request.ProdutoRequest;
import com.thawanlc.desafio.vosource.dto.response.ProdutoResponse;
import com.thawanlc.desafio.vosource.entity.Produto;

@Component
public class ProdutoMapper {

    public static Produto toEntity(ProdutoRequest request) {
        Produto produto = new Produto();
        produto.setCodigoBarras(request.codigoBarras());
        produto.setNome(request.nome());
        produto.setQuantidadeMinima(request.quantidadeMinima());
        return produto;
    }
    
    public static ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
            produto.getId(),
            produto.getCodigoBarras(),
            produto.getNome(),
            produto.getQuantidadeMinima(),
            produto.getSaldoInicial()
        );
    }


}
