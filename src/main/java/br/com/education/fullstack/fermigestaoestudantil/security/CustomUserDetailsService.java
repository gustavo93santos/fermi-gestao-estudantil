package br.com.education.fullstack.fermigestaoestudantil.security;

import br.com.education.fullstack.fermigestaoestudantil.entities.UsuarioEntity;
import br.com.education.fullstack.fermigestaoestudantil.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = this.repository.findByNomeUsuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado."));
        return new org.springframework.security.core.userdetails.User(usuario.getNomeUsuario(), usuario.getSenha(), new ArrayList<>());
    }
}
