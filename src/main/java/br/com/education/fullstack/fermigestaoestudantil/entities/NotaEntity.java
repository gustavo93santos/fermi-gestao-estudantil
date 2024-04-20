package br.com.education.fullstack.fermigestaoestudantil.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ge_t_nota")
public class NotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoEntity aluno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private DocenteEntity professor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "materia_id", nullable = false)
    private MateriaEntity materia;

    @Column(nullable = false)
    private Long valor;

    @Column(nullable = false)
    private Date data;
}
