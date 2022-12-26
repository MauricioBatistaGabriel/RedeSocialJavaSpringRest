package br.ovlac.redeSocial.repository;

import br.ovlac.redeSocial.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findByStudent_Id(long id);
}
