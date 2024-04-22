package br.com.education.fullstack.fermigestaoestudantil.controllers;

import br.com.education.fullstack.fermigestaoestudantil.dto.RegisterRequestDTO;
import br.com.education.fullstack.fermigestaoestudantil.dto.RespondeDTO;
import br.com.education.fullstack.fermigestaoestudantil.dto.loginRequestDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.UsuarioEntity;
import br.com.education.fullstack.fermigestaoestudantil.repositories.UsuarioRepository;
import br.com.education.fullstack.fermigestaoestudantil.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody loginRequestDTO body){
        //UsuarioEntity usuario = this.repository
        //        .findByNomeUsuario(body.nomeUsuario())
        //        .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        Optional<UsuarioEntity> usuario = this.repository
                .findByNomeUsuario(body.nomeUsuario());

        if(body.nomeUsuario() == null || body.nomeUsuario().isBlank() || body.senha() == null || body.senha().isBlank() ){
            return ResponseEntity.badRequest().build();
        } else if (passwordEncoder.matches(body.senha(), usuario.get().getSenha() )){
            String token = this.tokenService.gerarToken(usuario.get());
            return ResponseEntity.ok(new RespondeDTO(usuario.get().getNomeUsuario(), token));
        }
        return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/cadastro")
    public ResponseEntity register (@RequestBody RegisterRequestDTO body){
        Optional<UsuarioEntity> usuario = this.repository
                .findByNomeUsuario(body.nomeUsuario());
        if (usuario.isEmpty()){
            UsuarioEntity novoUsuario = new UsuarioEntity();
            novoUsuario.setSenha(passwordEncoder.encode(body.senha()));
            novoUsuario.setNomeUsuario(body.nomeUsuario());
            novoUsuario.setPapel(body.papel());
            this.repository.save(novoUsuario);
            String token = this.tokenService.gerarToken(novoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }
}
