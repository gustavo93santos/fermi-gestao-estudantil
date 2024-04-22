package br.com.education.fullstack.fermigestaoestudantil.controllers;

import br.com.education.fullstack.fermigestaoestudantil.dto.RegisterRequestDTO;
import br.com.education.fullstack.fermigestaoestudantil.dto.loginRequestDTO;
import br.com.education.fullstack.fermigestaoestudantil.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody loginRequestDTO body){
        return service.login(body);
    }

    @PostMapping("/cadastro")
    public ResponseEntity register (@RequestBody RegisterRequestDTO body){
        return service.create(body);
    }
}
