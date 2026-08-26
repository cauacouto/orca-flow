package com.couto.OrcaFlow.repository.ItemRepositori;

import com.couto.OrcaFlow.domin.ItemOrcamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemOrcamento,Integer> {
}
