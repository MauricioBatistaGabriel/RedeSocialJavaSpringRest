package br.ovlac.redeSocial.security;

import br.ovlac.redeSocial.model.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.lang.String;

@Service
public class TokenService {

    @Value("${capi.jwt.expiration}")
    private String expiration;

    @Value("${capi.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication){
        Student logado = (Student) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API do CapiCapi")
                .setSubject(String.valueOf(logado.getId()))
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token){
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Long getIdUsuario(String token){
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
