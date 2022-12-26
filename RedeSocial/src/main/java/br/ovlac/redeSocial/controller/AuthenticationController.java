package br.ovlac.redeSocial.controller;

import br.ovlac.redeSocial.controller.dto.TokenDto;
import br.ovlac.redeSocial.model.LoginForm;
import br.ovlac.redeSocial.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/loginStudent")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authetication(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken infoLogin = form.converter();
        try{
            Authentication authentication = authenticationManager.authenticate(infoLogin);
            System.out.println("AUTHENTICATION: " + authentication);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }
        catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
