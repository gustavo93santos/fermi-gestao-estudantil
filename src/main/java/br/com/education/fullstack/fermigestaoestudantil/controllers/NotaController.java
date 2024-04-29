package br.com.education.fullstack.fermigestaoestudantil.controllers;

import br.com.education.fullstack.fermigestaoestudantil.dto.MateriaDTO;
import br.com.education.fullstack.fermigestaoestudantil.dto.NotaDTO;
import br.com.education.fullstack.fermigestaoestudantil.services.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NotaController {

    private final NotaService service;

    @PostMapping("/notas")
    public ResponseEntity create (@RequestBody NotaDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(body));
    }

    @GetMapping("/notas/{id}")
    public ResponseEntity readById (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.readById(id));
    }

    @DeleteMapping("/notas/{id}")
    public ResponseEntity delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/notas/{id}")
    public ResponseEntity update (@PathVariable Long id, @RequestBody NotaDTO body){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, body));
    }

    @GetMapping("/alunos/{idAluno}/notas")
    public ResponseEntity readByAluno(@PathVariable Long idAluno){
        return ResponseEntity.status(HttpStatus.OK).body(service.readByAluno(idAluno));
    }

    @GetMapping("/alunos/{idAluno}/pontuacao")
    public ResponseEntity getPontuacaoAluno(@PathVariable Long idAluno){
        return ResponseEntity.status(HttpStatus.OK).body(service.getPontuacaoAluno(idAluno));
    }

}
