package br.ovlac.redeSocial.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "usuario")
public class Usuario {

    //RELACIONA O USUARIO COM SUAS POSTAGENS
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Postagem> postagens;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Size(max=40)
    @Getter
    @Setter
    private String usuario;

    @Size(max=70)
    @Getter
    @Setter
    private String email;

    @Size(max = 30)
    @Getter
    @Setter
    private String senha;

    @Getter
    @Setter
    private Boolean isDesabilitado = false;

    @Override
    public String toString() {
        return "Usuario{" +
                "postagens=" + postagens +
                ", id=" + id +
                ", usuario='" + usuario + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
