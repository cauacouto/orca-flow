package com.couto.OrcaFlow.service.orcamento;

import com.couto.OrcaFlow.Enum.StatusOrcamento;
import com.couto.OrcaFlow.domin.Cliente;
import com.couto.OrcaFlow.domin.ItemOrcamento;
import com.couto.OrcaFlow.domin.Orcamento;
import com.couto.OrcaFlow.domin.Usuario;
import com.couto.OrcaFlow.dto.ItemRequest;
import com.couto.OrcaFlow.dto.OrcamentoRequest;
import com.couto.OrcaFlow.dto.OrcamentoResponse;
import com.couto.OrcaFlow.mapper.OrcamentoMapper;
import com.couto.OrcaFlow.repository.ItemRepositori.ItemRepository;
import com.couto.OrcaFlow.repository.OrcamentoRepository.OrcamentoRepository;
import com.couto.OrcaFlow.repository.clientRepository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrcamentoService {

    private final OrcamentoRepository orcamentoRepository;
    private final ItemRepository itemRepository;
    private final OrcamentoMapper mapper;
    private final ClienteRepository clienteRepository;


    public void adicionarItem(UUID orcamentoId, ItemRequest request){

        this.orcamentoRepository.findById(orcamentoId)
                .orElseThrow(()
                        -> new RuntimeException("orcamento nao encontrado"));

        ItemOrcamento itemOrcamento = new ItemOrcamento();
        itemOrcamento.setNome(request.nome());

        itemOrcamento.setDescricao(request.descricao());

        itemOrcamento.setQuantidade(request.quantidade());

        itemOrcamento.setValorUnitario(request.valorUnitario());

        BigDecimal Total = request.valorUnitario().multiply(BigDecimal.valueOf(request.quantidade()));

        itemOrcamento.setTotal(Total);
        itemOrcamento.setOrcamentoId(orcamentoId);

        itemRepository.save(itemOrcamento);

    }


    public String gerarCodigo(UUID orcamentoId){
        Orcamento orcamento = orcamentoRepository.findById(orcamentoId)
                .orElseThrow(()-> new RuntimeException("orçamento nao ecnontrado"));

         String codigopublic = UUID.randomUUID().toString();
         orcamento.setCodigoPublico(codigopublic);

         return "urlteste//orcaflow,com/orcamento" + codigopublic;

    }



    public OrcamentoResponse criarOrcamento(OrcamentoRequest request, Usuario usuario) {

        Cliente cliente = clienteRepository.findByIdAndUsuario(request.clientId(),usuario)
                .orElseThrow(() -> new RuntimeException("cliente não encotrado"));

        if (!cliente.getUsuario().equals(usuario)){
            throw new RuntimeException("cliente não pertencente");
        }
        Orcamento orcamento = mapper.toEntity(request);
        orcamento.setUsuario(usuario);
        orcamento.setStatusOrcamento(StatusOrcamento.RASCUNHO);
        orcamento.setTotal(BigDecimal.ZERO);
        orcamentoRepository.save(orcamento);
        return mapper.toDto(orcamento);

    }
    public void excluriOrcamento(UUID orcamentoId){
        this.orcamentoRepository.deleteById(orcamentoId);
    }

    public void removerItems(UUID orcamentoId, Integer itemId){
        ItemOrcamento item = itemRepository.findById(itemId).orElseThrow(()->
                new RuntimeException("item não encontrado")
                );

        if (!item.getOrcamentoId().equals(orcamentoId)){
            throw new RuntimeException("Item não pertence ao orçamento");
        }
        itemRepository.deleteById(itemId);

    }

    public OrcamentoResponse buscarOrcamentoPorId(UUID id,Usuario usuario){
      Orcamento orcamento =  orcamentoRepository.findByIdAndUsuario(id,usuario)
                .orElseThrow(()-> new RuntimeException("orcamento nao encotrado"));
              return mapper.toDto(orcamento);
    }

    public List<OrcamentoResponse> listarOrcamento(Usuario usuario){
        List<Orcamento> orcamentos = orcamentoRepository.findByUsuario(usuario);
        return orcamentos.stream()
                .map(mapper::toDto)
                .toList();


    }


    }




