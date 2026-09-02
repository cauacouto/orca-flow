package com.couto.OrcaFlow.controller;

import com.couto.OrcaFlow.dto.ClientRequest;
import com.couto.OrcaFlow.dto.ClienteResponse;
import com.couto.OrcaFlow.service.cliente.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClientService clientService;


    @PostMapping
    public ResponseEntity<ClienteResponse> adicionarCliente(@RequestBody ClientRequest request){
        ClienteResponse response = clientService.criarCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atulizarClient(@RequestBody ClientRequest request, @PathVariable UUID id){
        this.clientService.atualizarCliente(request, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(UUID id){
        ClienteResponse response = clientService.buscarPorId(id);
        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(UUID id){
        this.clientService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
