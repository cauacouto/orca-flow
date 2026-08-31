package com.couto.OrcaFlow.controller;

import com.couto.OrcaFlow.domin.Usuario;
import com.couto.OrcaFlow.dto.UsuarioDto;
import com.couto.OrcaFlow.mapper.UsuarioMapper;
import com.couto.OrcaFlow.service.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OauthController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @RequestMapping("/")
    public String home(){
        return "hello word";
    }

   @GetMapping("/user")
   public UsuarioDto user(@AuthenticationPrincipal OAuth2User auth2User){
     Usuario usuario = usuarioService.buscarOuCriarEntidade(auth2User);
     return usuarioMapper.toResponse(usuario);
   }
}
