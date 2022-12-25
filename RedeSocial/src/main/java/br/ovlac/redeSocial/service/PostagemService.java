package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Postagem;
import br.ovlac.redeSocial.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

public interface PostagemService {

    public List<Postagem> findAll();
    public ResponseEntity<Optional<Postagem>> findById(long id);
    public List<Postagem> findByUsuario_Id(long id);
    public ResponseEntity<Postagem> create(Postagem newPostagem);
    public Postagem update(Postagem newPostagem, long id);
    public void deleteById(long id);
}
