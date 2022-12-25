package br.ovlac.redeSocial.repository;

import br.ovlac.redeSocial.model.Postagem;
import br.ovlac.redeSocial.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    public List<Postagem> findByUsuario_Id(long id);
}
