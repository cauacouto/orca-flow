package com.couto.OrcaFlow.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

    @RequestMapping("/")
    public String home(){
        return "hello word";
    }

    @GetMapping("/user")
    public OAuth2User user(@AuthenticationPrincipal OAuth2User user){
        return user;
    }
}
