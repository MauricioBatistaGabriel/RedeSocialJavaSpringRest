package br.ovlac.redeSocial.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity(name = "comentario")
public class Comentario {

    //RELACIONA OS COMENTARIOS COM SUA POSTAGEM
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postagem_id")
    private Postagem postagem;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 300)
    @Column(name = "comentario_usuario")
    @Getter
    @Setter
    private String comentario;

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
