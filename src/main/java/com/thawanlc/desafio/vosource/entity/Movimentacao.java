package com.thawanlc.desafio.vosource.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thawanlc.desafio.vosource.entity.enums.TipoMovimento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonIgnore
    private Produto produto;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovimento tipoMovimento;
    private int quantidade;
    private String descricao;
    private LocalDateTime dataHora;
    
    @Enumerated(EnumType.STRING)
    private TipoMovimento documento;

}
