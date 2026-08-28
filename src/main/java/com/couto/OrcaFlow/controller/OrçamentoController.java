package com.couto.OrcaFlow.controller;

import com.couto.OrcaFlow.domin.Usuario;
import com.couto.OrcaFlow.dto.ItemRequest;
import com.couto.OrcaFlow.dto.OrcamentoRequest;
import com.couto.OrcaFlow.dto.OrcamentoResponse;
import com.couto.OrcaFlow.service.orcamento.OrcamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orcamentos")
@PreAuthorize("hasAnyRole('PROFISSIONAL','ADMIN')")
@RequiredArgsConstructor
public class OrçamentoController {

    private final OrcamentoService service;


    @PostMapping()
    public ResponseEntity<OrcamentoResponse> criarOrcamento(@RequestBody OrcamentoRequest request, @AuthenticationPrincipal Usuario usuario){
        OrcamentoResponse response = service.criarOrcamento(request, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/itens")
    public ResponseEntity<Void> adicionarItem(@PathVariable UUID id, ItemRequest request){
        this.service.adicionarItem(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/itens/{itemId}")
    public ResponseEntity<Void> excluirItem(@PathVariable UUID id,@PathVariable Integer itemId){
        this.service.removerItems(id, itemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirOrcamento(@PathVariable UUID id){
        this.service.excluriOrcamento(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrcamentoResponse> buscarOrcamentoPorId(@PathVariable UUID id,@AuthenticationPrincipal Usuario usuario){
        OrcamentoResponse response = service.buscarOrcamentoPorId(id, usuario);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrcamentoResponse>> listarOrcamento(@AuthenticationPrincipal Usuario usuario){
       List<OrcamentoResponse> orcamentoResponses = service.listarOrcamento(usuario);
        return ResponseEntity.ok().body(orcamentoResponses);
    }
}
