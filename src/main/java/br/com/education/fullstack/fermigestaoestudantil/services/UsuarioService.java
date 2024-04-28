package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.RegisterRequestDTO;
import br.com.education.fullstack.fermigestaoestudantil.dto.ResponseDTO;
import br.com.education.fullstack.fermigestaoestudantil.dto.loginRequestDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.UsuarioEntity;
import br.com.education.fullstack.fermigestaoestudantil.repositories.UsuarioRepository;
import br.com.education.fullstack.fermigestaoestudantil.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final PapelService papelService;

    public UsuarioEntity readById(Long id){
        return repository.findById(id).orElse(null);
    }

    public void update(UsuarioEntity entity){
        this.repository.save(entity);
    }

    public ResponseEntity create(RegisterRequestDTO body){
        Optional<UsuarioEntity> usuario = this.repository
                .findByNomeUsuario(body.nomeUsuario());
        if (usuario.isEmpty()){
            UsuarioEntity novoUsuario = new UsuarioEntity();
            novoUsuario.setSenha(passwordEncoder.encode(body.senha()));
            novoUsuario.setNomeUsuario(body.nomeUsuario());
            novoUsuario.setPapel(papelService.readById(body.papel()));
            this.repository.save(novoUsuario);
            //String token = this.tokenService.gerarToken(novoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity login(loginRequestDTO body){
        Optional<UsuarioEntity> usuario = this.repository
                .findByNomeUsuario(body.nomeUsuario());

        if(body.nomeUsuario() == null || body.nomeUsuario().isBlank() || body.senha() == null || body.senha().isBlank() ){
            return ResponseEntity.badRequest().build();
        } else if (passwordEncoder.matches(body.senha(), usuario.get().getSenha() )){
            String token = this.tokenService.gerarToken(usuario.get());
            return ResponseEntity.ok(new ResponseDTO(usuario.get().getNomeUsuario(), token));
        }
        return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}
