package br.com.education.fullstack.fermigestaoestudantil.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ge_t_usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome_usuario")
    private String nomeUsuario;

    @Column(nullable = false)
    private String senha;

    @OneToOne
    @JoinColumn(name = "papel_id")
    private PapelEntity papel;
}
