package br.ovlac.redeSocial.controller;

import br.ovlac.redeSocial.model.Comentario;
import br.ovlac.redeSocial.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;

    @PostMapping("/novoComentario")
    public ResponseEntity<Comentario> novoComentario(@RequestBody Comentario newComentario){
        return comentarioService.create(newComentario);
    }

    @PutMapping("/editarComentario")
    public Comentario editarComentario(@RequestBody Comentario updateComentario, @PathVariable long id){
        return comentarioService.update(updateComentario, id);
    }

    @DeleteMapping("/deletarComentario")
    public void deletarComentario(@PathVariable long id){
        comentarioService.deleteById(id);
    }
}
