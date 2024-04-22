package br.com.education.fullstack.fermigestaoestudantil.services;

import br.com.education.fullstack.fermigestaoestudantil.dto.DocenteDTO;
import br.com.education.fullstack.fermigestaoestudantil.entities.DocenteEntity;
import br.com.education.fullstack.fermigestaoestudantil.entities.UsuarioEntity;
import br.com.education.fullstack.fermigestaoestudantil.repositories.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

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
            return null;
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

}
