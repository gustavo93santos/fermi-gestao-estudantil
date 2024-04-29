package br.com.education.fullstack.fermigestaoestudantil.repositories;

import br.com.education.fullstack.fermigestaoestudantil.entities.PapelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PapelRepository extends JpaRepository<PapelEntity, Long> {
    Optional<PapelEntity> findByNome(String nome);

}
