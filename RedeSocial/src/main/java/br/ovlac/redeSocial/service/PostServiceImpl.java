package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Post;
import br.ovlac.redeSocial.repository.PostRepository;
import br.ovlac.redeSocial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public ResponseEntity<Optional<Post>> findById(long id) {
        return ResponseEntity.ok().body(postRepository.findById(id));
    }

    @Override
    public List<Post> findByStudent_Id(long id) {
        return postRepository.findByStudent_Id(id);
    }

    @Override
    public ResponseEntity<Post> create(Post newPost) {
        return ResponseEntity.ok().body(postRepository.save(newPost));
    }

    @Override
    public Post update(Post updatePostagem, long id) {
        return postRepository.findById(id)
        .map(u -> {
            u.setSubtitle(updatePostagem.getSubtitle());
            return postRepository.save(u);
        })
                .orElseGet(() -> {
                    return postRepository.save(updatePostagem);
                });
    }

    @Override
    public void deleteById(long id) {
        postRepository.deleteById(id);
    }
}
