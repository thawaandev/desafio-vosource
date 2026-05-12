package com.thawanlc.desafio.vosource.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoBarras;

    private String nome;
    private int quantidadeMinima;
    private int saldoInicial;

    @OneToMany(mappedBy = "produto")
    @JsonManagedReference
    private List<Movimentacao> movimentacoes = new ArrayList<>();


}
