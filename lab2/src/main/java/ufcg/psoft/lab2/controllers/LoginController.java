package ufcg.psoft.lab2.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.psoft.lab2.entities.users.User;
import ufcg.psoft.lab2.entities.users.dtos.UserEmailPassword;
import ufcg.psoft.lab2.services.JwtService;
import ufcg.psoft.lab2.services.UserService;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final String TOKEN_KEY = "Xta1soF7UyJwk1VkecS89uh6HWEsimdi1hnsUDWz";
    private UserService userService;
    private JwtService jwtService;

    public LoginController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserEmailPassword user) throws ServletException {
        Optional<User> authUsuario = userService.getUser(user.getEmail());

        if (authUsuario.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (!authUsuario.get().getPassword().equals(user.getPassword())) {
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
    public ResponseEntity<User> removeUser(@PathVariable String email,
                                           @RequestHeader("Authorization") String header) throws ServletException {

        if (header == null || !header.startsWith("Bearer ") ||
                !this.jwtService.userHasPermission(header, email)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        String subject = this.jwtService.getTokenUser(header);

        return new ResponseEntity(this.userService.deleteUser(subject), HttpStatus.OK);
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
