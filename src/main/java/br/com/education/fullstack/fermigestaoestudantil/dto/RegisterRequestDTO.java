package br.com.education.fullstack.fermigestaoestudantil.dto;

import br.com.education.fullstack.fermigestaoestudantil.entities.PapelEntity;

public record RegisterRequestDTO (String nomeUsuario, String senha, PapelEntity papel){
}
