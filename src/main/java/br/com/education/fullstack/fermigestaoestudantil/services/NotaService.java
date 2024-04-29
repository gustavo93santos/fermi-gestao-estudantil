package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.MateriaDTO;
import br.com.education.fullstack.fermigestaoestudantil.dto.NotaDTO;
import br.com.education.fullstack.fermigestaoestudantil.dto.PontoacaoResponseDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.AlunoEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.DocenteEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.MateriaEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.NotaEntity;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.BadRequestException;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.NotFoundException;
import br.com.education.fullstack.fermigestaoestudantil.repositories.NotaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotaService {
    private final NotaRepository repository;
    private final AlunoService alunoService;
    private final MateriaService materiaService;
    private final DocenteService docenteService;

    public NotaEntity create(NotaDTO body) {
        log.info("NotaService: create: Inicio");
        if(     body.idAluno() == null ||
                body.idMateria() == null ||
                body.idProfessor() == null ||
                body.valor() == null ){
            log.error("NotaService: create: Dados invalidos para cadastro");
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
        log.info("NotaService: create: Nota adicionada");
        return nota;
    }

    public NotaEntity readById(Long id) {
        log.info("NotaService: readById: Inicio");
        return repository.findById(id).orElseThrow(() ->new NotFoundException("Nota não localizado"));
    }

    public NotaEntity update(Long id, NotaDTO body) {
        log.info("NotaService: update: Inicio");
        NotaEntity nota = readById(id);
        if( body.valor() == null ){
            log.error("NotaService: create: Dados invalidos para atualização da nota");
            throw new BadRequestException("Requisição invalida!");
        }
        nota.setValor(body.valor());
        nota.setData(new Date());

        repository.save(nota);
        log.info("NotaService: delete: Nota atualizada com sucesso");
        return nota;
    }

    public void delete (Long id) {
        log.info("NotaService: delete: Inicio");
        NotaEntity nota = readById(id);
        log.info("NotaService: delete: Nota deletada com sucesso");
        repository.delete(nota);
    }

    public List<NotaEntity> readByAluno (Long idAluno){
        AlunoEntity aluno = alunoService.readById(idAluno);
        return repository.findNotaEntitiesByAlunoEquals(aluno);
    }

    public PontoacaoResponseDTO getPontuacaoAluno (Long idAluno){
        List<NotaEntity> notas = readByAluno(idAluno);

        Long soma = 0L;
        for (NotaEntity n : notas) {
            soma += n.getValor();
        }

        PontoacaoResponseDTO pontuacao = new PontoacaoResponseDTO(soma/notas.size());
        return pontuacao;
    }
}
