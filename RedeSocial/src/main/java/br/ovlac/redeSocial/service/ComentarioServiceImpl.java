package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Comentario;
import br.ovlac.redeSocial.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServiceImpl implements ComentarioService{

    @Autowired
    ComentarioRepository comentarioRepository;

    @Override
    public ResponseEntity<List<Comentario>> findAll() {
        return ResponseEntity.ok().body(comentarioRepository.findAll());
    }

    @Override
    public ResponseEntity<Optional<Comentario>> findById(long id) {
        return ResponseEntity.ok().body(comentarioRepository.findById(id));
    }

    @Override
    public ResponseEntity<Comentario> create(Comentario newComentario) {
        if (newComentario == null)
            return ResponseEntity.ok(newComentario);

        if (newComentario != null)
            return ResponseEntity.ok().body(comentarioRepository.save(newComentario));
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public Comentario update(Comentario updateComentario, long id) {
        return comentarioRepository.findById(id)
                .map(u -> {
                    u.setComentario(updateComentario.getComentario());
                    return comentarioRepository.save(u);
                })
                .orElseGet(() -> {
                    return comentarioRepository.save(updateComentario);
                });
    }

    @Override
    public void deleteById(long id) {
        comentarioRepository.deleteById(id);
    }
}
