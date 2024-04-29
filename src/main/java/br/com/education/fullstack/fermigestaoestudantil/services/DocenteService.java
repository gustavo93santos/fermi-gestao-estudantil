package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.DocenteDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.DocenteEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.UsuarioEntity;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.BadRequestException;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.NotFoundException;
import br.com.education.fullstack.fermigestaoestudantil.repositories.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocenteService {
    private final DocenteRepository repository;
    private final UsuarioService usuarioService;
    private final PapelService papelService;

    public DocenteEntity create (DocenteDTO body){
        UsuarioEntity usuario = usuarioService.readById(body.idUsuario());
        if (usuario == null ||
                body.idUsuario() == null ||
                body.nome().isBlank() ||
                body.papel().isBlank()){
            throw new BadRequestException("Requisição invalida!");
        }

        usuario.setPapel(papelService.readByNome(body.papel().toUpperCase()));
        usuarioService.update(usuario);

        DocenteEntity docente = new DocenteEntity();
        docente.setNome(body.nome());
        docente.setDataEntrada(new Date());
        docente.setUsuario(usuario);
        this.repository.save(docente);

        return docente;
    }

    public DocenteEntity readById (Long id){
        return repository.findById(id).orElseThrow(() ->new NotFoundException("Docente não localizado"));
    }

    public List<DocenteEntity> read(){
        List<DocenteEntity> docentes = repository.findAll();
        if (docentes.isEmpty()) {
            throw new NotFoundException("Docente não localizado");
        }
        return docentes;
    }

    public DocenteEntity update(Long id, DocenteDTO body){
        DocenteEntity docente = readById(id);
        UsuarioEntity usuario = usuarioService.readById(body.idUsuario());

        if (usuario == null ||
                body.idUsuario() == null ||
                body.nome().isBlank() ||
                body.papel().isBlank()){
            throw new BadRequestException("Requisição invalida!");
        }

        docente.setNome(body.nome());
        docente.setUsuario(usuario);

        this.repository.save(docente);

        return docente;
    }

    public void delete(Long id){
        DocenteEntity docente = readById(id);
        repository.delete(docente);
    }

}
