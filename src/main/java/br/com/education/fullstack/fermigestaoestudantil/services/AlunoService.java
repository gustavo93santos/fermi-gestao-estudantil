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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;
    private final UsuarioService usuarioService;
    private final TurmaService turmaService;
    private final PapelService papelService;

    public AlunoEntity create(AlunoDTO body) {
        if (    body.nome().isBlank() ||
                body.dataNascimento() == null ||
                body.idTurma() == null ||
                body.idUsuario() == null){
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

        return aluno;
    }

    public AlunoEntity readById (Long id){
        return repository.findById(id).orElseThrow(() ->new NotFoundException("Aluno não localizado"));
    }

    public List<AlunoEntity> read(){
        List<AlunoEntity> alunos = repository.findAll();
        if (alunos.isEmpty()) {
            throw new NotFoundException("Aluno não localizado");
        }
        return alunos;
    }

    public AlunoEntity update(Long id, AlunoDTO body) {
        AlunoEntity aluno = readById(id);
        if (    body.nome().isBlank() ||
                body.dataNascimento() == null ||
                body.idTurma() == null ||
                body.idUsuario() == null){
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

        return aluno;
    }

    public void delete(Long id){
        AlunoEntity aluno = readById(id);
        repository.delete(aluno);
    }

}
