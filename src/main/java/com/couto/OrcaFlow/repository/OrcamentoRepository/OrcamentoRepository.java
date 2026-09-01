package com.couto.OrcaFlow.repository.OrcamentoRepository;

import com.couto.OrcaFlow.domin.Orcamento;
import com.couto.OrcaFlow.domin.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrcamentoRepository extends JpaRepository<Orcamento, UUID> {
    Optional<Orcamento> findByIdAndUsuario(UUID id, Usuario usuario);

    List<Orcamento> findByUsuario(Usuario usuario);


}
