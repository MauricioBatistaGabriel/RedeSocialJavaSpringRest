package br.ovlac.redeSocial.controller;

import br.ovlac.redeSocial.model.Comment;
import br.ovlac.redeSocial.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/newComment")
    public ResponseEntity<Comment> newComment(@RequestBody Comment newComment){
        return commentService.create(newComment);
    }

    @PutMapping("/editComment/{id}")
    public Comment editCommentById(@RequestBody Comment updateComment, @PathVariable long id){
        return commentService.update(updateComment, id);
    }

    @DeleteMapping("/deleteComment/{id}")
    public void deleteCommentById(@PathVariable long id){
        commentService.deleteById(id);
    }
}
