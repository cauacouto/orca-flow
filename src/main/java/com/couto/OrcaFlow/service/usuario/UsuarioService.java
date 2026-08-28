package com.couto.OrcaFlow.service.usuario;

import com.couto.OrcaFlow.Enum.Roles;
import com.couto.OrcaFlow.domin.Usuario;
import com.couto.OrcaFlow.dto.UsuarioDto;
import com.couto.OrcaFlow.mapper.UsuarioMapper;
import com.couto.OrcaFlow.repository.usuarioRepository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;

    public UsuarioDto buscarOuCriar(OAuth2User auth2User){

        String googleId = auth2User.getAttribute("sub");

        Usuario usuario = usuarioRepository.findByGoogleId(googleId).orElseGet(()->
                criarUsuario(auth2User));
        return mapper.toResponse(usuario);

    }

    private Usuario criarUsuario(OAuth2User auth2User){

        Usuario usuario = new Usuario();
        usuario.setGoogleId(auth2User.getAttribute("sub"));
        usuario.setEmail(auth2User.getAttribute("email"));
        usuario.setName(auth2User.getAttribute("name"));
        usuario.setPicture(auth2User.getAttribute("picture"));
        usuario.setRole(Roles.PROFISSIONAL);
        usuario.setOnboardingCompleted(false);

        return usuarioRepository.save(usuario);


    }

    public void atualizar(UUID usuarioId,String novoNome){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new RuntimeException("usuario não encontrado"));
           usuario.setName(novoNome);
           usuarioRepository.save(usuario);

    }




}
