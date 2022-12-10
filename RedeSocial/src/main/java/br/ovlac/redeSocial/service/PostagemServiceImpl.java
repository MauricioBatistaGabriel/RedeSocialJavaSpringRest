package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Postagem;
import br.ovlac.redeSocial.model.Usuario;
import br.ovlac.redeSocial.repository.PostagemRepository;
import br.ovlac.redeSocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostagemServiceImpl implements PostagemService{

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Postagem> findAll() {
        return postagemRepository.findAll();
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

        Usuario UsuarioAux = usuarioRepository.findById(newPostagem.getIdUsuario()).get();
        newPostagem.setUsuario(UsuarioAux);
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
