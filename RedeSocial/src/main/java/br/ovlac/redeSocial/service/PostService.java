package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {

    public List<Post> findAll();
    public ResponseEntity<Optional<Post>> findById(long id);
    public List<Post> findByStudent_Id(long id);
    public ResponseEntity<Post> create(Post newPost);
    public Post update(Post newPost, long id);
    public void deleteById(long id);
}
