package com.thawanlc.desafio.vosource.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ProdutoRequest(
    @NotBlank String codigoBarras,
    @NotBlank String nome,
    int quantidadeMinima,
    int saldoInicial
) {
    
}
