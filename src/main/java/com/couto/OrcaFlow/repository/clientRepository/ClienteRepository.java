package com.couto.OrcaFlow.repository.clientRepository;

import com.couto.OrcaFlow.domin.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
