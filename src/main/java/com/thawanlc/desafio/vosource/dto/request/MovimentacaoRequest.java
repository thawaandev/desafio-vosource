package com.thawanlc.desafio.vosource.dto.request;

import com.thawanlc.desafio.vosource.entity.enums.TipoMovimento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovimentacaoRequest(
    @NotBlank Long produtoId,
    @NotBlank TipoMovimento tipoMovimento,
    Integer quantidade,
    @NotNull String descricao
) {
    
}
