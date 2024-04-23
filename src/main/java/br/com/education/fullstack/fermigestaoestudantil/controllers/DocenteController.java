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
    public ResponseEntity register (@RequestBody DocenteDTO body){
        DocenteEntity docente = service.create(body);
        if (docente != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(docente);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity readById (@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }
}
