package br.com.education.fullstack.fermigestaoestudantil.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class testController {
    @GetMapping
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("Sucesso");
    }
}
