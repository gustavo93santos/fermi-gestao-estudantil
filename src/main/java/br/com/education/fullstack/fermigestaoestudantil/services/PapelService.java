package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.entities.PapelEntity;
import br.com.education.fullstack.fermigestaoestudantil.repositories.PapelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PapelService {

    private final PapelRepository repository;

    public PapelEntity readById(Long id){
        log.info("PapelService: readById: Inicio ");
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Papel especificado não encontrado"));
    }

    public PapelEntity readByNome(String nome){
        log.info("PapelService: readByNome: Inicio ");
        return repository.findByNome(nome).orElseThrow(() -> new RuntimeException("Papel especificado não encontrado"));
    }

}
