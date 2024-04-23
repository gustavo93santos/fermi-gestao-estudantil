package br.com.education.fullstack.fermigestaoestudantil.exceptions;

import br.com.education.fullstack.fermigestaoestudantil.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handler(NotFoundException e ){
        ExceptionDTO excetion = ExceptionDTO
                .builder()
                .codigo(HttpStatus.BAD_REQUEST.value())
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.status(excetion.codigo()).body(excetion);
    }
}
