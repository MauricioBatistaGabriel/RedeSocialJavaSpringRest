package br.ovlac.redeSocial.controller;

import br.ovlac.redeSocial.model.Usuario;
import br.ovlac.redeSocial.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/dadosPessoais")
    public Optional<Usuario> buscaUsuarioById(long id){
        return usuarioService.findById(id);
    }

    @GetMapping("/usuarios")
    public List<Usuario> listaUsuarios(){
        return usuarioService.findAll();
    }

    @GetMapping("/usuarioByNome")
    public List<Usuario> listaUsuariopNome(@RequestBody String usuarioNome){
        return usuarioService.findByUsuario(usuarioNome);
    }

    @PostMapping("/novoCadastro")
    public Usuario cadastrarUsuario(@RequestBody Usuario newUsuario){
             return usuarioService.registrar(newUsuario);
    }

    @PostMapping("/login")
    public Usuario logarUsuario(@RequestBody String usuario, String senha){
            return usuarioService.findByUsuarioAndSenha(usuario, senha);
    }

    @PutMapping("/editarUsuario/{id}")
    public Usuario editarUsuario(@RequestBody Usuario updateUsuario,@PathVariable Long id){
        return usuarioService.update(updateUsuario, id);
    }

    @DeleteMapping("/deletarUsuario/{id}")
    public void deletarUsuario(@PathVariable Long id){
        usuarioService.deleteById(id);
    }

    @GetMapping("/recuperarSenhaUsuario")
    public void recuperarSenhaUsuarioByEmail(@RequestBody Usuario usuario, String email, @PathVariable long id){

        int qtdeMaximaCaracteres = 8;
        String[] caracteres = {"1", "2", "4", "5", "6", "7", "8",
                "9","0" , "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z" };

        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < qtdeMaximaCaracteres; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            senha.append(caracteres[posicao]);
        }
        usuario.setSenha(senha.toString());
        usuarioService.update(usuario, id);
        usuarioService.recuperacaoSenha(email, senha.toString());
    }
}
