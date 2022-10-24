package br.ovlac.redeSocial.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "usuario")
public class Usuario implements UserDetails {

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

    @Size(max = 300)
    @Getter
    @Setter
    private String senha;

    @Getter
    @Setter
    private Boolean isDesabilitado = false;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    @Override
    public String toString() {
        return "Usuario{" +
                "postagens=" + postagens +
                ", id=" + id +
                ", usuario='" + usuario + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
