package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.MateriaDTO;
import br.com.education.fullstack.fermigestaoestudantil.dto.NotaDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.AlunoEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.DocenteEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.MateriaEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.NotaEntity;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.BadRequestException;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.NotFoundException;
import br.com.education.fullstack.fermigestaoestudantil.repositories.NotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotaService {
    private final NotaRepository repository;
    private final AlunoService alunoService;
    private final MateriaService materiaService;
    private final DocenteService docenteService;

    public NotaEntity create(NotaDTO body) {
        if(     body.idAluno() == null ||
                body.idMateria() == null ||
                body.idProfessor() == null ||
                body.valor() == null ){
            throw new BadRequestException("Requisição invalida!");
        }

        AlunoEntity aluno = alunoService.readById(body.idAluno());
        DocenteEntity docente = docenteService.readById(body.idProfessor());
        MateriaEntity materia = materiaService.readById(body.idMateria());

        NotaEntity nota = new NotaEntity();
        nota.setAluno(aluno);
        nota.setMateria(materia);
        nota.setProfessor(docente);
        nota.setValor(body.valor());
        nota.setData(new Date());

        repository.save(nota);
        return nota;
    }

    public NotaEntity readById(Long id) {
        return repository.findById(id).orElseThrow(() ->new NotFoundException("Nota não localizado"));
    }

    public NotaEntity update(Long id, NotaDTO body) {
        NotaEntity nota = readById(id);
        if( body.valor() == null ){
            throw new BadRequestException("Requisição invalida!");
        }
        nota.setValor(body.valor());
        nota.setData(new Date());

        repository.save(nota);
        return nota;
    }

    public void delete (Long id) {
        NotaEntity nota = readById(id);
        repository.delete(nota);
    }

    public List<NotaEntity> readByAluno (Long idAluno){
        AlunoEntity aluno = alunoService.readById(idAluno);
        return repository.findNotaEntitiesByAlunoEquals(aluno);
    }
}
