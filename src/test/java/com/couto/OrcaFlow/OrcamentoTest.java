package com.couto.OrcaFlow;

import com.couto.OrcaFlow.domin.Usuario;
import com.couto.OrcaFlow.dto.OrcamentoRequest;
import com.couto.OrcaFlow.dto.OrcamentoResponse;
import com.couto.OrcaFlow.mapper.OrcamentoMapper;
import com.couto.OrcaFlow.repository.OrcamentoRepository.OrcamentoRepository;
import com.couto.OrcaFlow.service.orcamento.OrcamentoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.couto.OrcaFlow.Enum.StatusOrcamento;
import com.couto.OrcaFlow.domin.Orcamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class OrcamentoTest {

    @Mock
    private OrcamentoRepository orcamentoRepository;

  @Mock
    private OrcamentoMapper orcamentoMapper;

    @InjectMocks
    private OrcamentoService orcamentoService;

    @Test
    void deveDefinirObservacaoDoOrcamento(){
        Orcamento orcamento = new Orcamento(StatusOrcamento.RASCUNHO, LocalDate.of(2026,4,8)
                ,"fazer encanamento",new BigDecimal("150.00"));
        Assertions.assertEquals("fazer encanamento", orcamento.getObservacao());

    }

    @Test
    void deveCriarOrcamentoo(){

        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        UUID idbanco = UUID.randomUUID();
        String codigo = "tetstetet";


        UUID clientId = UUID.randomUUID();
        LocalDate data = LocalDate.of(2026,5,6);



        OrcamentoRequest request = new OrcamentoRequest(
                clientId,
                "test1",
                data,

                "tomada"

        );

        Orcamento orcamento = new Orcamento();


        Mockito.when(orcamentoMapper.toEntity(request))
                .thenReturn(orcamento);




        Mockito.when(orcamentoRepository.save(Mockito.any(Orcamento.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        OrcamentoResponse response = new OrcamentoResponse(
                idbanco,
                codigo,
                orcamento.getNomeOrcamento(),
                clientId,
                StatusOrcamento.RASCUNHO,
                data,
                "tomada",
                BigDecimal.ZERO
        );


        Mockito.when(orcamentoMapper.toDto(orcamento))
                .thenReturn(response);

        OrcamentoResponse resultado = orcamentoService.criarOrcamento(request,usuario);



            Assertions.assertNotNull(resultado);
        Assertions.assertEquals(StatusOrcamento.RASCUNHO,resultado.status());
        Assertions.assertEquals(BigDecimal.ZERO,resultado.valorTotal());

        Mockito.verify(orcamentoRepository)
                .save(Mockito.any(Orcamento.class));





    }


}
