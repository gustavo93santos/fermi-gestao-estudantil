package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.TurmaDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.TurmaEntity;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.BadRequestException;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.NotFoundException;
import br.com.education.fullstack.fermigestaoestudantil.repositories.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurmaService {
    private final TurmaRepository repository;
    private final CursoService cursoService;
    private final DocenteService docenteService;

    public TurmaEntity create (TurmaDTO body){
        if(body.nome().isBlank() || body.idCurso() == null || body.idProfessor() == null){
            throw new BadRequestException("Requisição invalida!");
        }
        TurmaEntity turma = new TurmaEntity();
        turma.setNome(body.nome());
        turma.setProfessor(docenteService.readById(body.idProfessor()));
        turma.setCurso(cursoService.readById(body.idCurso()));
        repository.save(turma);
        return turma;
    }

    public List<TurmaEntity> read (){
        List<TurmaEntity> turmas = repository.findAll();
        if(turmas.isEmpty()){
            throw new NotFoundException("Turma não localizado");
        }
        return turmas;
    }

    public TurmaEntity readById (Long id){
        return repository.findById(id).orElseThrow(() ->new NotFoundException("Turma não localizado"));
    }

    public void delete(Long id){
        TurmaEntity turma = readById(id);
        repository.delete(turma);
    }

    public TurmaEntity update (Long id, TurmaDTO body){
        if(body.nome().isBlank() || body.idCurso() == null || body.idProfessor() == null){
            throw new BadRequestException("Requisição invalida!");
        }
        TurmaEntity turma = readById(id);

        turma.setNome(body.nome());
        turma.setProfessor(docenteService.readById(body.idProfessor()));
        turma.setCurso(cursoService.readById(body.idCurso()));
        repository.save(turma);
        return turma;
    }


}
