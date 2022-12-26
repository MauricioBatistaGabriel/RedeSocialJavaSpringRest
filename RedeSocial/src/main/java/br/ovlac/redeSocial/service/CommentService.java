package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    public ResponseEntity<List<Comment>> findAll();
    public ResponseEntity<Optional<Comment>> findById(long id);
    public ResponseEntity<Comment> create(Comment newComment);
    public Comment update(Comment updateComment, long id);
    public void deleteById(long id);
}
