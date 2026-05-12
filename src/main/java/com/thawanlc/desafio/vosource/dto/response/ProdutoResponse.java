package com.thawanlc.desafio.vosource.dto.response;

public record ProdutoResponse(
    Long id,
    String codigoBarras,
    String nome,
    int quantidadeMinima,
    int saldoInicial
) {
    
}
