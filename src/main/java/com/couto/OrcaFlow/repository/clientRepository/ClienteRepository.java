package com.couto.OrcaFlow.repository.clientRepository;

import com.couto.OrcaFlow.domin.Cliente;
import com.couto.OrcaFlow.domin.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByIdAndUsuario(UUID id, Usuario usuario);
}
