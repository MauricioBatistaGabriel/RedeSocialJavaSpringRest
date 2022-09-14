package br.ovlac.redeSocial.repository;

import br.ovlac.redeSocial.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByUsuarioAndSenha(String usuario, String senha);
    public List<Usuario> findByUsuario(String usuarioNome);
}
