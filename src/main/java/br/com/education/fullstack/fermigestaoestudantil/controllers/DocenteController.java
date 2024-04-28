package br.com.education.fullstack.fermigestaoestudantil.controllers;

import br.com.education.fullstack.fermigestaoestudantil.dto.DocenteDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.DocenteEntity;
import br.com.education.fullstack.fermigestaoestudantil.services.DocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/docentes")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteService service;

    @PostMapping
    public ResponseEntity create (@RequestBody DocenteDTO body){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(body));
    }

    @GetMapping("{id}")
    public ResponseEntity readById (@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.readById(id));
    }

    @GetMapping
    public ResponseEntity read () {
        return ResponseEntity.status(HttpStatus.OK).body(service.read());
    }

    @PutMapping("{id}")
    public ResponseEntity update (@PathVariable Long id, @RequestBody DocenteDTO body) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, body));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
