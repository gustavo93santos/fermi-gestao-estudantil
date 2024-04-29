package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.RegisterRequestDTO;
import br.com.education.fullstack.fermigestaoestudantil.dto.ResponseDTO;
import br.com.education.fullstack.fermigestaoestudantil.dto.loginRequestDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.UsuarioEntity;
import br.com.education.fullstack.fermigestaoestudantil.repositories.UsuarioRepository;
import br.com.education.fullstack.fermigestaoestudantil.security.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final PapelService papelService;

    public UsuarioEntity readById(Long id){
        log.info("UsuarioService: readById ", id);
        UsuarioEntity usuario = repository.findById(id).orElse(null);
        log.info("UsuarioService: readById: Usuario encontrado ", usuario);
        return usuario;
    }

    public void update(UsuarioEntity entity){
        log.info("UsuarioService: update: Inicio");
        this.repository.save(entity);
        log.info("UsuarioService: update: Usuario Atualizado");
    }

    public ResponseEntity create(RegisterRequestDTO body){
        log.info("UsuarioService: create: Inicio");
        Optional<UsuarioEntity> usuario = this.repository
                .findByNomeUsuario(body.nomeUsuario());
        if (usuario.isEmpty()){
            UsuarioEntity novoUsuario = new UsuarioEntity();
            novoUsuario.setSenha(passwordEncoder.encode(body.senha()));
            novoUsuario.setNomeUsuario(body.nomeUsuario());
            novoUsuario.setPapel(papelService.readById(body.papel()));
            this.repository.save(novoUsuario);
            //String token = this.tokenService.gerarToken(novoUsuario);
            log.info("UsuarioService: create: Usuario criado");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        log.error("UsuarioService: create: Usuario ja existe", usuario.get());
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity login(loginRequestDTO body){
        log.info("UsuarioService: login: Inicio");
        Optional<UsuarioEntity> usuario = this.repository
                .findByNomeUsuario(body.nomeUsuario());

        if(body.nomeUsuario() == null || body.nomeUsuario().isBlank() || body.senha() == null || body.senha().isBlank() ){
            log.error("UsuarioService: login: Dados invalidos");
            return ResponseEntity.badRequest().build();
        } else if (passwordEncoder.matches(body.senha(), usuario.get().getSenha() )){
            String token = this.tokenService.gerarToken(usuario.get());
            log.info("UsuarioService: login: Login realizado com sucesso");
            return ResponseEntity.ok(new ResponseDTO(usuario.get().getNomeUsuario(), token));
        }
        log.error("UsuarioService: login: Login não autorizado");
        return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}
