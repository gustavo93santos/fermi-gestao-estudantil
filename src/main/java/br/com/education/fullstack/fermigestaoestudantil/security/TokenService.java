package br.com.education.fullstack.fermigestaoestudantil.security;

import br.com.education.fullstack.fermigestaoestudantil.entities.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(UsuarioEntity usuario){

        try{
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("fermi-gestao-estudantil")
                    .withSubject(usuario.getNomeUsuario())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algoritimo);
        }catch (JWTCreationException e){
            //TODO criar Exception personalizada
            throw new RuntimeException("Erro de autenticação!!");
        }
    }

    public String validarToken(String token){
        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    .withIssuer("fermi-gestao-estudantil")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e){
            return null;
        }
    }

    private Instant gerarDataExpiracao(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }

}
