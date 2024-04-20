package br.com.education.fullstack.fermigestaoestudantil.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ge_t_curso")
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    //TODO - Implementar listas de objetos (turmas e materias).
    //private List<TurmaEntity> turmas;
    //private List<MateriaEntity> materias;
}
