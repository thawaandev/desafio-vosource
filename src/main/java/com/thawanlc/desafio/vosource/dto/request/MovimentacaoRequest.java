package com.thawanlc.desafio.vosource.dto.request;

import com.thawanlc.desafio.vosource.entity.enums.TipoMovimento;

import jakarta.validation.constraints.NotNull;

public record MovimentacaoRequest(
    @NotNull Long produtoId,
    @NotNull TipoMovimento tipoMovimento,
    @NotNull String descricao,
    Integer quantidade
) {
    
}
