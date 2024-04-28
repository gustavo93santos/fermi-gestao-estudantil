package br.com.education.fullstack.fermigestaoestudantil.controllers;

import br.com.education.fullstack.fermigestaoestudantil.dto.CursoDTO;
import br.com.education.fullstack.fermigestaoestudantil.dto.MateriaDTO;
import br.com.education.fullstack.fermigestaoestudantil.services.MateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MateriaController {

    private final MateriaService service;

    @PostMapping("/materias")
    public ResponseEntity create (@RequestBody MateriaDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(body));
    }

    @GetMapping("/materias/{id}")
    public ResponseEntity readById (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.readById(id));
    }

    @DeleteMapping("/materias/{id}")
    public ResponseEntity delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/materias/{id}")
    public ResponseEntity update (@PathVariable Long id, @RequestBody MateriaDTO body){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, body));
    }

    @GetMapping("/cursos/{idCurso}/materias")
    public ResponseEntity readByCurso(@PathVariable Long idCurso){
        return ResponseEntity.status(HttpStatus.OK).body(service.readByCurso(idCurso));
    }
}
