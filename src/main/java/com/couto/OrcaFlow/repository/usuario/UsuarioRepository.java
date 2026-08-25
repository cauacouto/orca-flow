package com.couto.OrcaFlow.repository.usuario;

import com.couto.OrcaFlow.domin.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>

{
    Optional<Usuario> findByGoogleId(String googleId);

}
