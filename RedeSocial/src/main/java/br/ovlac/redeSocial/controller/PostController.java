package br.ovlac.redeSocial.controller;

import br.ovlac.redeSocial.model.Post;
import br.ovlac.redeSocial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<Post> listPosts(){
        return postService.findAll();
    }

    @GetMapping("/postById/{id}")
    public ResponseEntity<Optional<Post>> searchPostById(long id){
        return postService.findById(id);
    }


    @PostMapping("/newPost")
    public ResponseEntity<Post> createNewPost(@RequestBody Post newPost){
        return postService.create(newPost);
    }

    @PutMapping("/editPost/{id}")
    public Post editPostById(@RequestBody Post updatePost, @PathVariable long id){
        return postService.update(updatePost, id);
    }

    @DeleteMapping("deletePost/{id}")
    public void deletPostById(@PathVariable long id){
        postService.deleteById(id);
    }
}
