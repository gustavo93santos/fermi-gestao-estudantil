package br.com.education.fullstack.fermigestaoestudantil.repositories;

import br.com.education.fullstack.fermigestaoestudantil.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByNomeUsuario(String nomeUsuario);
}
