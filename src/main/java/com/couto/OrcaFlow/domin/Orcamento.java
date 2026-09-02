package com.couto.OrcaFlow.domin;

import com.couto.OrcaFlow.Enum.StatusOrcamento;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orcamentos_db")
@Data


public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;
     private String nomeOrcamento;
    @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;

    private UUID clienteId;

    @OneToMany(
            mappedBy = "orcamento",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemOrcamento> itemsList = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private StatusOrcamento statusOrcamento = StatusOrcamento.RASCUNHO;

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

     public void enviar(){
       if (statusOrcamento != StatusOrcamento.RASCUNHO){
           throw  new IllegalArgumentException("somente orçamento em rascunho pode ser enviado ");
       }
       this.statusOrcamento = StatusOrcamento.ENVIADO;
     }


     public void adicionarItem(ItemOrcamento item){
       itemsList.add(item);
       item.setOrcamento(this);
     }
}
