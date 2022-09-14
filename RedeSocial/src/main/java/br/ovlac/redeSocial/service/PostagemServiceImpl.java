package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Postagem;
import br.ovlac.redeSocial.model.Usuario;
import br.ovlac.redeSocial.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostagemServiceImpl implements PostagemService{

    @Autowired
    PostagemRepository postagemRepository;

    @Override
    public List<Postagem> findAll() {
        return postagemRepository.findAll();
    }

    @Override
    public Optional<Postagem> findById(long id) {
        return postagemRepository.findById(id);
    }

    @Override
    public List<Postagem> findByUsuario(String usuario){
        return postagemRepository.findByUsuario(usuario);
    }

    @Override
    public Postagem create(Postagem newPostagem) {
        if (newPostagem == null)
            return newPostagem;

        if (newPostagem != null)
            return postagemRepository.save(newPostagem);

        return newPostagem;
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
