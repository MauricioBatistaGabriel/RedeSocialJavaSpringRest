package br.ovlac.redeSocial.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity(name = "postagem")
public class Postagem {

    //RELACIONA A POSTAGEM COM O USUARIO
    @Setter
    //GERA PROBLEMA AO RETORNAR POSTAGEM COM USUARIO
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @Getter
    @Setter
    private long idUsuario;

    //RELACIONA A POSTAGEM COM VARIOS COMENTARIOS
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postagem")
    private List<Comentario> comentarios;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 100)
    @Getter
    @Setter
    private String legenda;

    @Getter
    @Setter
    private Date dataAtual = new Date();

//    @Getter
//    @Setter
//    private List<Byte> imagens;

    @Override
    public String  toString() {
        return "Postagem{" +
                "username=" + usuario.getUsername() +
                ", legenda='" + legenda + '\'' +
                '}';
    }
}
