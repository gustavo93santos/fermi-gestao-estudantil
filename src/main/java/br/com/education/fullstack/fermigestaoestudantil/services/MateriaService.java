package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.MateriaDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.CursoEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.MateriaEntity;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.BadRequestException;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.NotFoundException;
import br.com.education.fullstack.fermigestaoestudantil.repositories.MateriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MateriaService {
    private final MateriaRepository repository;
    private final CursoService cursoService;

    public MateriaEntity create (MateriaDTO body){
        log.info("MateriaService: create: Inicio");
        if(body.nome().isBlank() || body.idCurso() == null){
            log.error("MateriaService: create: Dados invalidos para cadastro");
            throw new BadRequestException("Requisição invalida!");
        }
        MateriaEntity materia = new MateriaEntity();
        materia.setNome(body.nome());
        materia.setCurso(cursoService.readById(body.idCurso()));
        repository.save(materia);
        log.info("MateriaService: create: Materia criada");
        return materia;
    }

    public MateriaEntity readById(Long id){
        log.info("MateriaService: readById: Inicio");
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Materia não localizada"));
    }

    public void delete(Long id){
        log.info("MateriaService: delete: Inicio");
        MateriaEntity materia = readById(id);
        repository.delete(materia);
        log.info("MateriaService: delete: realizado com sucesso");
    }

    public MateriaEntity update(Long id, MateriaDTO body){
        log.info("MateriaService: update: Inicio");
        MateriaEntity materia = readById(id);
        if(body.nome().isBlank() || body.idCurso() == null){
            log.error("MateriaService: update: Dados invalidos para atualização de materia");
            throw new BadRequestException("Requisição invalida!");
        }
        materia.setNome(body.nome());
        materia.setCurso(cursoService.readById(body.idCurso()));
        repository.save(materia);
        return materia;
    }

    public List<MateriaEntity> readByCurso(Long idCurso){
        log.info("MateriaService: readByCurso: Inicio");
        CursoEntity curso = cursoService.readById(idCurso);
        return repository.findMateriaEntitiesByCursoEquals(curso);
    }
}
