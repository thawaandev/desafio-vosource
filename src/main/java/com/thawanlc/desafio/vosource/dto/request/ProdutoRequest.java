package com.thawanlc.desafio.vosource.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequest(
    @NotBlank String codigoBarras,
    @NotBlank String nome,
    @NotNull int quantidadeMinima,
    int saldoInicial
) {
    
}
