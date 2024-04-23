package br.com.education.fullstack.fermigestaoestudantil.dto;

import lombok.Builder;

@Builder
public record ExceptionDTO(int codigo, String mensagem) {
}
