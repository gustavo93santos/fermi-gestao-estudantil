package br.com.education.fullstack.fermigestaoestudantil.repositories;

import br.com.education.fullstack.fermigestaoestudantil.entities.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<DocenteEntity, Long> {
}
