package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.MateriaDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.CursoEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.MateriaEntity;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.BadRequestException;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.NotFoundException;
import br.com.education.fullstack.fermigestaoestudantil.repositories.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriaService {
    private final MateriaRepository repository;
    private final CursoService cursoService;

    public MateriaEntity create (MateriaDTO body){
        if(body.nome().isBlank() || body.idCurso() == null){
            throw new BadRequestException("Requisição invalida!");
        }
        MateriaEntity materia = new MateriaEntity();
        materia.setNome(body.nome());
        materia.setCurso(cursoService.readById(body.idCurso()));
        repository.save(materia);
        return materia;
    }

    public MateriaEntity readById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Materia não localizada"));
    }

    public void delete(Long id){
        MateriaEntity materia = readById(id);
        repository.delete(materia);
    }

    public MateriaEntity update(Long id, MateriaDTO body){
        MateriaEntity materia = readById(id);
        if(body.nome().isBlank() || body.idCurso() == null){
            throw new BadRequestException("Requisição invalida!");
        }
        materia.setNome(body.nome());
        materia.setCurso(cursoService.readById(body.idCurso()));
        repository.save(materia);
        return materia;
    }

    public List<MateriaEntity> readByCurso(Long idCurso){
        CursoEntity curso = cursoService.readById(idCurso);
        return repository.findMateriaEntitiesByCursoEquals(curso);
    }
}
