package br.com.education.fullstack.fermigestaoestudantil.controllers;

import br.com.education.fullstack.fermigestaoestudantil.dto.TurmaDTO;
import br.com.education.fullstack.fermigestaoestudantil.services.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turmas")
@RequiredArgsConstructor
public class TurmaController {
    private final TurmaService service;

    @PostMapping
    public ResponseEntity create (@RequestBody TurmaDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(body));
    }

    @GetMapping
    public ResponseEntity read () {
        return ResponseEntity.status(HttpStatus.OK).body(service.read());
    }

    @GetMapping("{id}")
    public ResponseEntity readById (@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.readById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("{id}")
    public ResponseEntity update (@PathVariable Long id, @RequestBody TurmaDTO body) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, body));
    }
}
