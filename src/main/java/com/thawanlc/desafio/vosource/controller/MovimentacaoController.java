package com.thawanlc.desafio.vosource.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thawanlc.desafio.vosource.dto.request.MovimentacaoRequest;
import com.thawanlc.desafio.vosource.dto.response.MovimentacaoResponse;
import com.thawanlc.desafio.vosource.entity.Movimentacao;
import com.thawanlc.desafio.vosource.entity.enums.TipoMovimento;
import com.thawanlc.desafio.vosource.service.MovimentacaoService;

@RestController
@RequestMapping("/api/movs")
public class MovimentacaoController {
    
    @Autowired private MovimentacaoService movimentacaoService;

    @PostMapping("/lancar")
    public ResponseEntity<MovimentacaoResponse> salvarMovimentacao(@RequestBody MovimentacaoRequest request) {
        movimentacaoService.lancarMovimentacao(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping
    public List<MovimentacaoResponse> buscarTodos() {
        return movimentacaoService.buscarTodos();
    }

    @GetMapping("/filtrarmovimentacao")
    public List<Movimentacao> filtrarPorMovimentacao(@RequestParam TipoMovimento tipo) {
        return movimentacaoService.filtrarPorTipoMovimentacao(tipo);
    }
    
    @PutMapping("/{id}")
    public MovimentacaoResponse atualizarMovimentacao(@PathVariable Long id, @RequestParam boolean isEntrada, @RequestBody MovimentacaoRequest update) {
        return movimentacaoService.registrarAjuste(id, isEntrada, update);
    }

    @GetMapping("/filtrarperiodo")
    public List<Movimentacao> buscarPorProdutoEPeriodo(@RequestParam Long produtoId, @RequestParam String inicio, @RequestParam String fim) {
    LocalDateTime dataInicio = LocalDateTime.parse(inicio);
    LocalDateTime dataFim = LocalDateTime.parse(fim);

    return movimentacaoService.buscarPorProdutoEPeriodo(produtoId, dataInicio, dataFim);
}

}
