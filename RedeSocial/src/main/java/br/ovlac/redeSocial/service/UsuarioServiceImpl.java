package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Usuario;
import br.ovlac.redeSocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private JavaMailSender javaMailSender;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario findByUsuarioAndSenha(String usuario, String senha) {
        return usuarioRepository.findByUsuarioAndSenha(usuario, senha) != null ? usuarioRepository.findByUsuarioAndSenha(usuario, senha) : null;
    }

    @Override
    public List<Usuario> findByUsuario(String usuarioNome){
        return usuarioRepository.findByUsuario(usuarioNome);
    }

    @Override
    public Usuario registrar(Usuario newUsuario) {
        if (newUsuario == null)
            return newUsuario;
        if (newUsuario != null)
            usuarioRepository.save(newUsuario);
        return newUsuario;
    }

    @Override
    public Usuario update(Usuario updateUsuario, long id) {
        return usuarioRepository.findById(id)
        .map(u -> {
            u.setUsuario(updateUsuario.getUsuario());
            u.setSenha(updateUsuario.getSenha());
            return usuarioRepository.save(u);
        })
                .orElseGet(() -> {
                    return usuarioRepository.save(updateUsuario);
                });
    }

    @Override
    public void deleteById(long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void desabilitarUsuario(Usuario desabilitaUsuario) {
        desabilitaUsuario.setIsDesabilitado(true);
    }

    @Override
    public void habilitarUsuario(Usuario habilitaUsuario) {
        habilitaUsuario.setIsDesabilitado(false);
    }

    @Override
    public void recuperacaoSenha(String para, String conteudo){
        var mensagem = new SimpleMailMessage();
        mensagem.setTo(para);
        mensagem.setSubject("Recuperação de senha!");
        mensagem.setText(conteudo);
        javaMailSender.send(mensagem);
    }
}
