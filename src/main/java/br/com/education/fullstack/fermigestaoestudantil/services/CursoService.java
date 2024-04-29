package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.CursoDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.CursoEntity;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.BadRequestException;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.NotFoundException;
import br.com.education.fullstack.fermigestaoestudantil.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository repository;

    public CursoEntity create(CursoDTO body){
        log.info("CursoService: create: Inicio");
        if (body.nome().isBlank()){
            log.error("CursoService: update: Dados invalidos para cadastro de curso");
            throw new BadRequestException("Requisição invalida!");
        }

        CursoEntity curso = new CursoEntity();
        curso.setNome(body.nome());
        this.repository.save(curso);
        log.info("CursoService: create: Curso criado");
        return curso;
    }

    public CursoEntity readById(Long id){
        log.info("CursoService: readById: Inicio");
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Curso não localizado"));
    }

    public List<CursoEntity> read(){
        log.info("CursoService: read: Inicio");
        List<CursoEntity> cursos = repository.findAll();
        if (cursos.isEmpty()){
            log.error("CursoService: read: Não foram localizadas Cursos cadastrados");
            throw new NotFoundException("Curso não localizado");
        }
        return cursos;
    }

    public CursoEntity update(Long id, CursoDTO body){
        log.info("CursoService: update: Inicio");
        CursoEntity curso = readById(id);
        if (body.nome().isBlank()){
            log.error("CursoService: update: Dados invalidos para atualização de curso");
            throw new BadRequestException("Requisição invalida!");
        }
        curso.setNome(body.nome());
        this.repository.save(curso);
        log.info("CursoService: update: Curso atualizado");
        return curso;
    }

    public void delete(Long id){
        log.info("CursoService: delete: Inicio");
        CursoEntity curso = readById(id);
        this.repository.delete(curso);
        log.info("CursoService: delete: Delete realizado com sucesso");
    }



}
