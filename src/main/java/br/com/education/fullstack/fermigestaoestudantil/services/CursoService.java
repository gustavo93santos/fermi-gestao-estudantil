package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.CursoDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.CursoEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.DocenteEntity;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.BadRequestException;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.NotFoundException;
import br.com.education.fullstack.fermigestaoestudantil.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository repository;

    public CursoEntity create(CursoDTO body){
        if (body.nome().isBlank()){
            throw new BadRequestException("Requisição invalida!");
        }

        CursoEntity curso = new CursoEntity();
        curso.setNome(body.nome());
        repository.save(curso);
        return curso;
    }

    //TODO Adicionar busca para completar objeto
    public CursoEntity readById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Curso não localizado"));
    }

    public List<CursoEntity> read(){
        List<CursoEntity> cursos = repository.findAll();
        if (cursos.isEmpty()){
            throw new NotFoundException("Curso não localizado");
        }
        return cursos;
    }

    //TODO Implementar alterações
    public CursoEntity update(Long id, CursoDTO body){
        CursoEntity curso = new CursoEntity();
        return curso;
    }

    public void delete(Long id){
        CursoEntity curso = readById(id);
        repository.delete(curso);
    }



}
