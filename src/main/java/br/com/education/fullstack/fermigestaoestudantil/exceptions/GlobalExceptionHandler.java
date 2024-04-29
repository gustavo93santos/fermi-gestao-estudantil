package br.com.education.fullstack.fermigestaoestudantil.exceptions;

import br.com.education.fullstack.fermigestaoestudantil.dto.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handler(NotFoundException e ){
        log.error("NotFoundException: Entidade não localizada", e.getMessage());
        ExceptionDTO excetion = ExceptionDTO
                .builder()
                .codigo(HttpStatus.NOT_FOUND.value())
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.status(excetion.codigo()).body(excetion);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDTO> handler(BadRequestException e ){
        log.error("BadRequestException: Dados invalidos informados", e.getMessage());
        ExceptionDTO excetion = ExceptionDTO
                .builder()
                .codigo(HttpStatus.BAD_REQUEST.value())
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.status(excetion.codigo()).body(excetion);
    }
}
