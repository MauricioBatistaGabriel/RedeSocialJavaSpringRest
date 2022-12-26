package br.ovlac.redeSocial.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity(name = "comment")
public class Comment {

    //RELACIONA OS COMENTARIOS COM SUA POSTAGEM
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @Setter
    private Post post;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Size(max = 300)
    @Column(name = "commentStudent")
    @Getter
    @Setter
    private String comment;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", Comment='" + comment + '\'' +
                '}';
    }
}
