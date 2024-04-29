package br.com.education.fullstack.fermigestaoestudantil.repositories;

import br.com.education.fullstack.fermigestaoestudantil.entities.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
}
