package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.TurmaDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.TurmaEntity;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.BadRequestException;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.NotFoundException;
import br.com.education.fullstack.fermigestaoestudantil.repositories.TurmaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TurmaService {
    private final TurmaRepository repository;
    private final CursoService cursoService;
    private final DocenteService docenteService;

    public TurmaEntity create (TurmaDTO body){
        log.info("TurmaService: create: Inicio");
        if(body.nome().isBlank() || body.idCurso() == null || body.idProfessor() == null){
            log.error("TurmaService: create: Dados invalidos para cadastro");
            throw new BadRequestException("Requisição invalida!");
        }
        TurmaEntity turma = new TurmaEntity();
        turma.setNome(body.nome());
        turma.setProfessor(docenteService.readById(body.idProfessor()));
        turma.setCurso(cursoService.readById(body.idCurso()));
        repository.save(turma);
        log.info("TurmaService: create: Turma criada");
        return turma;
    }

    public List<TurmaEntity> read (){
        log.info("TurmaService: read: Inicio");
        List<TurmaEntity> turmas = repository.findAll();
        if(turmas.isEmpty()){
            log.error("TurmaService: read: Não foram localizadas turmas cadastradas");
            throw new NotFoundException("Turma não localizado");
        }
        return turmas;
    }

    public TurmaEntity readById (Long id){
        log.info("TurmaService: readById: Inicio");
        return repository.findById(id).orElseThrow(() ->new NotFoundException("Turma não localizado"));
    }

    public void delete(Long id){
        log.info("TurmaService: delete: Inicio");
        TurmaEntity turma = readById(id);
        repository.delete(turma);
        log.info("TurmaService: delete: Delete realizado com sucesso");
    }

    public TurmaEntity update (Long id, TurmaDTO body){
        log.info("TurmaService: update: Inicio");
        if(body.nome().isBlank() || body.idCurso() == null || body.idProfessor() == null){
            log.error("TurmaService: update: Dados invalidos para atualização de turma");
            throw new BadRequestException("Requisição invalida!");
        }
        TurmaEntity turma = readById(id);

        turma.setNome(body.nome());
        turma.setProfessor(docenteService.readById(body.idProfessor()));
        turma.setCurso(cursoService.readById(body.idCurso()));
        repository.save(turma);
        log.info("TurmaService: update: Turma atualizada");
        return turma;
    }


}
