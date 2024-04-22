package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.entities.PapelEntity;
import br.com.education.fullstack.fermigestaoestudantil.repositories.PapelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PapelService {

    private final PapelRepository repository;

    public PapelEntity readById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Papel especificado não encontrado"));
    }

}
