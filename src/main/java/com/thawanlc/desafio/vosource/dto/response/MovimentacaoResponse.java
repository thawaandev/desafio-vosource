package com.thawanlc.desafio.vosource.dto.response;

import java.time.LocalDateTime;

import com.thawanlc.desafio.vosource.entity.enums.TipoMovimento;

public record MovimentacaoResponse(
    Long id,
    String produtoName,
    TipoMovimento tipoMovimento,
    LocalDateTime dataHora,
    int quantidade,
    String descricao,
    TipoMovimento documento
) {
    
}
