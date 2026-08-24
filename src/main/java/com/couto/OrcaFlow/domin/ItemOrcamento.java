package com.couto.OrcaFlow.domin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Item_orcamento_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orcamentoId;
    private String descricao;
    private int quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal total;
}
