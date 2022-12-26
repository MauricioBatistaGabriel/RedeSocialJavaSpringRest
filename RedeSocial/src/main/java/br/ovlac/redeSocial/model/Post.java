package br.ovlac.redeSocial.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity(name = "post")
public class Post {

    //RELACIONA A POSTAGEM COM O ESTUDANTE
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    //RELACIONA A POSTAGEM COM VARIOS COMENTARIOS
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    @Getter
    private List<Comment> comments;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Size(max = 100)
    @Getter
    @Setter
    private String subtitle;

    @Getter
    @Setter
    private Date data = new Date();

    @Override
    public String  toString() {
        return "Post{" +
                ", subtitle='" + subtitle + '\'' +
                '}';
    }
}
