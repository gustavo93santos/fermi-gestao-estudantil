package br.com.education.fullstack.fermigestaoestudantil.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ge_t_materia")
public class MateriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne (optional = false)
    @JoinColumn(name = "curso_id", nullable = false)
    //TODO retornar cursoEntity sem os list
    @JsonIgnore
    private CursoEntity curso;

}
