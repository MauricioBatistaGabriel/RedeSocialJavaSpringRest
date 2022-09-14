package br.ovlac.redeSocial.controller;

import br.ovlac.redeSocial.model.Postagem;
import br.ovlac.redeSocial.model.Usuario;
import br.ovlac.redeSocial.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @GetMapping("/postagemById/{id}")
    public Optional<Postagem> buscaPostagemById(long id){
        return postagemService.findById(id);
    }

    @GetMapping("/postagens")
    public List<Postagem> listaPostagens(){
        return postagemService.findAll();
    }

    @GetMapping("/postagemUsuarioByNome")
    public List<Postagem> listarPostagemByUsuarioName(@RequestBody Usuario usuario){
        return postagemService.findByUsuario(usuario.getUsuario());
    }

    @PostMapping("/novaPostagem")
    public Postagem criarPostagem(@RequestBody Postagem newPostagem){
        return postagemService.create(newPostagem);
    }

    @PutMapping("/editarPostagem/{id}")
    public Postagem editarPostagem(@RequestBody Postagem updatePostagem, @PathVariable long id){
        return postagemService.update(updatePostagem, id);
    }

    @DeleteMapping("deletarPostagem/{id}")
    public void deletarPostagem(@PathVariable long id){
        postagemService.deleteById(id);
    }
}
