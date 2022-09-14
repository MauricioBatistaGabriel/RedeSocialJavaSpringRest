package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UsuarioService {

    public List<Usuario> findAll();
    public Optional<Usuario> findById(long id);
    public Usuario findByUsuarioAndSenha(String usuario, String senha);
    public List<Usuario> findByUsuario(String usuarioNome);
    public Usuario registrar(Usuario newUsuario);
    public Usuario update(Usuario updateUsuario, long id);
    public void deleteById(long id);
    public void desabilitarUsuario(Usuario desabilitaUsuario);
    public void habilitarUsuario(Usuario desabilitaUsuario);
    public void recuperacaoSenha(String para, String conteudo);
}
