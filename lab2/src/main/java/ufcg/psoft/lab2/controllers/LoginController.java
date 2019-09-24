package ufcg.psoft.lab2.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.psoft.lab2.entities.usuarios.Usuario;
import ufcg.psoft.lab2.entities.usuarios.dtos.UserEmailPassword;
import ufcg.psoft.lab2.services.JwtService;
import ufcg.psoft.lab2.services.UsuarioService;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final String TOKEN_KEY = "Xta1soF7UyJwk1VkecS89uh6HWEsimdi1hnsUDWz";
    private UsuarioService usuarioService;
    private JwtService jwtService;

    public LoginController(UsuarioService usuarioService, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserEmailPassword user) throws ServletException {
        Optional<Usuario> authUsuario = usuarioService.getUsuario(user.getEmail());

        if (authUsuario.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (!authUsuario.get().getSenha().equals(user.getPassword())) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        String token = Jwts.builder()
                .setSubject(authUsuario.get().getEmail())
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .compact();

        return new ResponseEntity(new LoginResponse(token), HttpStatus.OK);
    }

    @DeleteMapping("/usuarios/{email}")
    public ResponseEntity<Usuario> removeUser(@PathVariable String email,
                                              @RequestHeader("Authorization") String header) throws ServletException {

        if (header == null || !header.startsWith("Bearer ") ||
                !this.jwtService.userHasPermission(header, email)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        String subject = this.jwtService.getTokenUser(header);

        return new ResponseEntity(this.usuarioService.deleteUser(subject), HttpStatus.OK);
    }

    private class LoginResponse {
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}
