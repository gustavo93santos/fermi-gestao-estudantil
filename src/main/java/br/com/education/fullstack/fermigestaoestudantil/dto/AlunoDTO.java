package br.com.education.fullstack.fermigestaoestudantil.dto;

import java.util.Date;

public record AlunoDTO(String nome, Date dataNascimento, Long idUsuario, Long idTurma) {
}
