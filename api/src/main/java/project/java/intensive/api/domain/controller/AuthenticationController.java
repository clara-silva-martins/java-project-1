package project.java.intensive.api.domain.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.java.intensive.api.domain.user.DataAthentication;
import project.java.intensive.api.domain.user.User;
import project.java.intensive.api.infra.security.DataTokenJWT;
import project.java.intensive.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataAthentication data){
        var tokenAuthentication = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(tokenAuthentication);

        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

      return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }



}
