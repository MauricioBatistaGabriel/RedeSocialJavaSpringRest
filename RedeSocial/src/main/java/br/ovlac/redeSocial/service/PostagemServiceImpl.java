package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Postagem;
import br.ovlac.redeSocial.model.Usuario;
import br.ovlac.redeSocial.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostagemServiceImpl implements PostagemService{

    @Autowired
    PostagemRepository postagemRepository;

    @Override
    public ResponseEntity<List<Postagem>> findAll() {
        return ResponseEntity.ok().body(postagemRepository.findAll());
    }

    @Override
    public ResponseEntity<Optional<Postagem>> findById(long id) {
        return ResponseEntity.ok().body(postagemRepository.findById(id));
    }

    @Override
    public ResponseEntity<List<Postagem>> findByUsuario(String usuario){
        return ResponseEntity.ok().body(postagemRepository.findByUsuario(usuario));
    }

    @Override
    public ResponseEntity<Postagem> create(Postagem newPostagem) {
        if (newPostagem == null)
            return ResponseEntity.ok().body(newPostagem);

        else
            return ResponseEntity.ok().body(postagemRepository.save(newPostagem));
    }

    @Override
    public Postagem update(Postagem updatePostagem, long id) {
        return postagemRepository.findById(id)
        .map(u -> {
            u.setLegenda(updatePostagem.getLegenda());
            return postagemRepository.save(u);
        })
                .orElseGet(() -> {
                    return postagemRepository.save(updatePostagem);
                });
    }

    @Override
    public void deleteById(long id) {
        postagemRepository.deleteById(id);
    }
}
