package com.thawanlc.desafio.vosource.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.thawanlc.desafio.vosource.dto.request.MovimentacaoRequest;
import com.thawanlc.desafio.vosource.dto.response.MovimentacaoResponse;
import com.thawanlc.desafio.vosource.entity.Movimentacao;

@Component
public class MovimentacaoMapper {
    
    public static MovimentacaoResponse toResponse(Movimentacao m) {
        return new MovimentacaoResponse(
            m.getId(),
            m.getProduto().getNome(),
            m.getTipoMovimento(),
            m.getDataHora(),
            m.getQuantidade(),
            m.getDescricao(),
            m.getDocumento()
        );
    } 

    public static void merge(Movimentacao movimentacao, MovimentacaoRequest update) {
        if(update.quantidade() != null) {
            movimentacao.setQuantidade(update.quantidade());
        }
        if(update.descricao() != null) {
            movimentacao.setDescricao(update.descricao());
            movimentacao.setDocumento(update.tipoMovimento());
        }
    }


    public static Movimentacao toEntity(MovimentacaoRequest request) {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipoMovimento(request.tipoMovimento());
        movimentacao.setQuantidade(request.quantidade());
        movimentacao.setDataHora(LocalDateTime.now());
        return movimentacao;
    }

}
