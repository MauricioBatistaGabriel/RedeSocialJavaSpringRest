package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Comment;
import br.ovlac.redeSocial.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public ResponseEntity<List<Comment>> findAll() {
        return ResponseEntity.ok().body(commentRepository.findAll());
    }

    @Override
    public ResponseEntity<Optional<Comment>> findById(long id) {
        return ResponseEntity.ok().body(commentRepository.findById(id));
    }

    @Override
    public ResponseEntity<Comment> create(Comment newComment) {
        if (newComment == null)
            return ResponseEntity.ok(newComment);

        if (newComment != null)
            return ResponseEntity.ok().body(commentRepository.save(newComment));
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public Comment update(Comment updateComment, long id) {
        return commentRepository.findById(id)
                .map(u -> {
                    u.setComment(updateComment.getComment());
                    return commentRepository.save(u);
                })
                .orElseGet(() -> {
                    return commentRepository.save(updateComment);
                });
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}
