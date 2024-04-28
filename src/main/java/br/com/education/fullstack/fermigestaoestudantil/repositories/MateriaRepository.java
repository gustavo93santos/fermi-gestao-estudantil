package br.com.education.fullstack.fermigestaoestudantil.repositories;

import br.com.education.fullstack.fermigestaoestudantil.entities.CursoEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {

    List<MateriaEntity> findMateriaEntitiesByCursoEquals(CursoEntity curso);

}
