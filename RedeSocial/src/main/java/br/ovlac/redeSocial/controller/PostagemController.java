package br.ovlac.redeSocial.controller;

import br.ovlac.redeSocial.model.Postagem;
import br.ovlac.redeSocial.model.Usuario;
import br.ovlac.redeSocial.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @GetMapping("/postagens")
    public List<Postagem> listaPostagens(){
        return postagemService.findAll();
    }

    @GetMapping("/postagemById/{id}")
    public ResponseEntity<Optional<Postagem>> buscaPostagemById(long id){
        return postagemService.findById(id);
    }


    @PostMapping("/novaPostagem")
    public ResponseEntity<Postagem> criarPostagem(@RequestBody Postagem newPostagem){
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
