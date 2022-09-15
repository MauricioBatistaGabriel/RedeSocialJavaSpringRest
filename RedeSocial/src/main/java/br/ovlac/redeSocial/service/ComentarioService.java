package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Comentario;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ComentarioService {

    public ResponseEntity<List<Comentario>> findAll();
    public ResponseEntity<Optional<Comentario>> findById(long id);
    public ResponseEntity<Comentario> create(Comentario newComentario);
    public Comentario update(Comentario updateComentario, long id);
    public void deleteById(long id);
}
