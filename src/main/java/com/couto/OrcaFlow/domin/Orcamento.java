package com.couto.OrcaFlow.domin;

import com.couto.OrcaFlow.Enum.StatusOrcamento;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "orcamentos_db")
@Data


public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;

    private UUID clienteId;

    private Long codigoPublico;

    @Enumerated(EnumType.STRING)
    private StatusOrcamento statusOrcamento;

    private LocalDate validade;

    private String observacao;

    private BigDecimal total;

   public Orcamento(){}
    public Orcamento(StatusOrcamento statusOrcamento, LocalDate validade, String observacao, BigDecimal total) {
        this.statusOrcamento = statusOrcamento;
        this.validade = validade;
        this.observacao = observacao;
        this.total = total;
    }
}
