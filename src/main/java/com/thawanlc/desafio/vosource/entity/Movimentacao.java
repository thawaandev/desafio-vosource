package com.thawanlc.desafio.vosource.entity;

import java.time.LocalDateTime;

import com.thawanlc.desafio.vosource.entity.enums.TipoMovimento;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Produto produto;
    private TipoMovimento tipoMovimento;
    private int quantidade;
    private String descricao;
    private LocalDateTime dataHora;
    private TipoMovimento documento;

}
