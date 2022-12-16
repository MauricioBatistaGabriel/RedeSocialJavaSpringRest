package br.ovlac.redeSocial.service;

import br.ovlac.redeSocial.model.Usuario;
import br.ovlac.redeSocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private JavaMailSender javaMailSender;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok().body(usuarioRepository.findAll());
    }

    @Override
    public ResponseEntity<Optional<Usuario>> findById(long id) {
        return ResponseEntity.ok().body(usuarioRepository.findById(id));
    }

    @Override
    public ResponseEntity<Usuario> findByUsuarioAndSenha(String usuario, String senha) {
        return usuarioRepository.findByUsuarioAndSenha(usuario, senha) != null ?
            ResponseEntity.ok().body(usuarioRepository.findByUsuarioAndSenha(usuario, senha)) : ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<List<Usuario>> findByUsuario(String usuarioNome){
        return ResponseEntity.ok().body(usuarioRepository.findByUsuario(usuarioNome));
    }

    @Override
    public ResponseEntity<Usuario> registrar(Usuario newUsuario) {
        if (newUsuario == null)
            return ResponseEntity.badRequest().build();
        else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = newUsuario.getSenha();
            newUsuario.setSenha(encoder.encode(password));
            return ResponseEntity.ok().body(usuarioRepository.save(newUsuario));
        }
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
