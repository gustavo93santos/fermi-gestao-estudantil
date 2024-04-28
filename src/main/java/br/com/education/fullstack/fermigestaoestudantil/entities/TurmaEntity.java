package br.com.education.fullstack.fermigestaoestudantil.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "ge_t_turma")
public class TurmaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "turma")
    private List<AlunoEntity> alunos;

    @ManyToOne(optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private DocenteEntity professor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "curso_id", nullable = false)
    private CursoEntity curso;
}
