package br.com.education.fullstack.fermigestaoestudantil.repositories;

import br.com.education.fullstack.fermigestaoestudantil.entities.AlunoEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
    List<NotaEntity> findNotaEntitiesByAlunoEquals(AlunoEntity aluno);
}
