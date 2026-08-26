package com.couto.OrcaFlow.domin;

import com.couto.OrcaFlow.Enum.StatusOrcamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "orcamentos_db")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private UUID id;
    @Column(nullable = false)
    private UUID usuarioId;
    private UUID clienteId;
    private Long codigoPublico;
    @Enumerated(EnumType.STRING)
    private StatusOrcamento statusOrcamento;
    private LocalDate validade;
    private String observacao;
    private BigDecimal total;
}
