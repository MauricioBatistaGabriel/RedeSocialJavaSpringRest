package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public ResponseEntity<List<Usuario>> findAll();
    public ResponseEntity<Optional<Usuario>> findById(long id);
    public ResponseEntity<Usuario> findByUsuarioAndSenha(String usuario, String senha);
    public ResponseEntity<List<Usuario>> findByUsuario(String usuarioNome);
    public ResponseEntity<Usuario> registrar(Usuario newUsuario);
    public Usuario update(Usuario updateUsuario, long id);
    public void deleteById(long id);
    public void desabilitarUsuario(Usuario desabilitaUsuario);
    public void habilitarUsuario(Usuario desabilitaUsuario);
    public void recuperacaoSenha(String para, String conteudo);
}
