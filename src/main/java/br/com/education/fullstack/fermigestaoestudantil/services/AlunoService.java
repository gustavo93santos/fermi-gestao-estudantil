package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.AlunoDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.AlunoEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.DocenteEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.TurmaEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.UsuarioEntity;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.BadRequestException;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.NotFoundException;
import br.com.education.fullstack.fermigestaoestudantil.repositories.AlunoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;
    private final UsuarioService usuarioService;
    private final TurmaService turmaService;
    private final PapelService papelService;

    public AlunoEntity create(AlunoDTO body) {
        log.info("AlunoService: create: Inicio");
        if (    body.nome().isBlank() ||
                body.dataNascimento() == null ||
                body.idTurma() == null ||
                body.idUsuario() == null){
            log.error("AlunoService: create: Dados invalidos para cadastro");
            throw new BadRequestException("Requisição invalida!");
        }
        UsuarioEntity usuario = usuarioService.readById(body.idUsuario());
        usuario.setPapel(papelService.readByNome("ALUNO"));
        usuarioService.update(usuario);

        TurmaEntity turma = turmaService.readById(body.idTurma());

        AlunoEntity aluno = new AlunoEntity();
        aluno.setNome(body.nome());
        aluno.setDataNascimento(body.dataNascimento());
        aluno.setUsuario(usuario);
        aluno.setTurma(turma);

        repository.save(aluno);
        log.info("AlunoService: create: Aluno criado");
        return aluno;
    }

    public AlunoEntity readById (Long id){
        log.info("AlunoService: readById: Inicio");
        return repository.findById(id).orElseThrow(() ->new NotFoundException("Aluno não localizado"));
    }

    public List<AlunoEntity> read(){
        log.info("AlunoService: read: Inicio");
        List<AlunoEntity> alunos = repository.findAll();
        if (alunos.isEmpty()) {
            log.error("AlunoService: read: Não foram localizadas Alunos cadastrados");
            throw new NotFoundException("Aluno não localizado");
        }
        return alunos;
    }

    public AlunoEntity update(Long id, AlunoDTO body) {
        log.info("AlunoService: update: Inicio");
        AlunoEntity aluno = readById(id);
        if (    body.nome().isBlank() ||
                body.dataNascimento() == null ||
                body.idTurma() == null ||
                body.idUsuario() == null){
            log.error("AlunoService: create: Dados invalidos para atualização");
            throw new BadRequestException("Requisição invalida!");
        }
        UsuarioEntity usuario = usuarioService.readById(body.idUsuario());
        usuario.setPapel(papelService.readByNome("ALUNO"));
        usuarioService.update(usuario);

        TurmaEntity turma = turmaService.readById(body.idTurma());

        aluno.setNome(body.nome());
        aluno.setDataNascimento(body.dataNascimento());
        aluno.setUsuario(usuario);
        aluno.setTurma(turma);

        repository.save(aluno);
        log.info("AlunoService: update: Turma atualizada");
        return aluno;
    }

    public void delete(Long id){
        log.info("AlunoService: delete: Inicio");
        AlunoEntity aluno = readById(id);
        repository.delete(aluno);
        log.info("AlunoService: delete: Delete realizado com sucesso");
    }

}
