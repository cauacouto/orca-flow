package com.couto.OrcaFlow.service.cliente;

import com.couto.OrcaFlow.domin.Cliente;
import com.couto.OrcaFlow.dto.ClientRequest;
import com.couto.OrcaFlow.dto.ClienteResponse;
import com.couto.OrcaFlow.mapper.ClientMapper;
import com.couto.OrcaFlow.repository.clientRepository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClienteRepository clienteRepository;
    private final ClientMapper mapper;


    public ClienteResponse criarCliente(ClientRequest request){
        Cliente cliente = mapper.toEntity(request);
        cliente.setNome(request.nome());
        cliente.setTelefone(request.telefone());
        clienteRepository.save(cliente);
        return mapper.toDto(cliente);

    }

    public void atualizarCliente(ClientRequest request, UUID id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()->
                new RuntimeException("cliente não encontrado"));

        cliente.setNome(request.nome());
        cliente.setTelefone(request.telefone());
        clienteRepository.save(cliente);
    }

    public ClienteResponse buscarPorId(UUID id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()->
                new RuntimeException("cliente nao encotrado"));

        return mapper.toDto(cliente);

    }


    public void deletar(UUID clientId){
        this.clienteRepository.deleteById(clientId);
    }


}
