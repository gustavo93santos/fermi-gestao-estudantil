package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.DocenteDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.DocenteEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.UsuarioEntity;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.BadRequestException;
import br.com.education.fullstack.fermigestaoestudantil.exceptions.NotFoundException;
import br.com.education.fullstack.fermigestaoestudantil.repositories.DocenteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocenteService {
    private final DocenteRepository repository;
    private final UsuarioService usuarioService;
    private final PapelService papelService;

    public DocenteEntity create (DocenteDTO body){
        log.info("DocenteService: create: Inicio");
        if (    body.idUsuario() == null ||
                body.nome().isBlank() ||
                body.papel().isBlank()){
            log.error("DocenteService: create: Dados invalidos para cadastro");
            throw new BadRequestException("Requisição invalida!");
        }
        UsuarioEntity usuario = usuarioService.readById(body.idUsuario());
        usuario.setPapel(papelService.readByNome(body.papel().toUpperCase()));
        usuarioService.update(usuario);

        DocenteEntity docente = new DocenteEntity();
        docente.setNome(body.nome());
        docente.setDataEntrada(new Date());
        docente.setUsuario(usuario);
        this.repository.save(docente);
        log.info("DocenteService: update: Docente criado");
        return docente;
    }

    public DocenteEntity readById (Long id){
        log.info("DocenteService: readById: Inicio");
        return repository.findById(id).orElseThrow(() ->new NotFoundException("Docente não localizado"));
    }

    public List<DocenteEntity> read(){
        log.info("DocenteService: read: Inicio");
        List<DocenteEntity> docentes = repository.findAll();
        if (docentes.isEmpty()) {
            log.error("DocenteService: read: Não foram localizadas docentes cadastrados");
            throw new NotFoundException("Docente não localizado");
        }
        return docentes;
    }

    public DocenteEntity update(Long id, DocenteDTO body){
        log.info("DocenteService: update: Inicio");
        DocenteEntity docente = readById(id);

        if (    body.idUsuario() == null ||
                body.nome().isBlank() ||
                body.papel().isBlank()){
            log.error("DocenteService: create: Dados invalidos para atualização de Docente");
            throw new BadRequestException("Requisição invalida!");
        }
        UsuarioEntity usuario = usuarioService.readById(body.idUsuario());
        usuario.setPapel(papelService.readByNome(body.papel().toUpperCase()));
        usuarioService.update(usuario);

        docente.setNome(body.nome());
        docente.setUsuario(usuario);

        this.repository.save(docente);
        log.info("DocenteService: update: Docente atualizado");
        return docente;
    }

    public void delete(Long id){
        log.info("DocenteService: delete: Inicio");
        DocenteEntity docente = readById(id);
        repository.delete(docente);
        log.info("DocenteService: delete: Delete realizado com sucesso");
    }

}
