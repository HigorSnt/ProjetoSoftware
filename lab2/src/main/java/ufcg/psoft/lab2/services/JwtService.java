package ufcg.psoft.lab2.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;
import ufcg.psoft.lab2.entities.usuarios.Usuario;

import javax.servlet.ServletException;
import java.util.Optional;

@Service
public class JwtService {

    private UsuarioService usuarioService;
    private final int TOKEN_INDEX = 7;
    private final String TOKEN_KEY = "Xta1soF7UyJwk1VkecS89uh6HWEsimdi1hnsUDWz";

    public JwtService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public boolean userHasPermission(String authorizationHeader, String email) throws ServletException {
        String subject = getTokenUser(authorizationHeader);

        Optional<Usuario> optUsuario = this.usuarioService.getUsuario(subject);

        return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email);
    }

    public String getTokenUser(String authorizationHeader) throws ServletException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ServletException("Token inexistente ou mal formatado!");
        }

        String token = authorizationHeader.substring(TOKEN_INDEX);
        String subject = null;

        try {
            subject = Jwts.parser()
                    .setSigningKey(TOKEN_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (SignatureException e) {
            throw new ServletException("Token inválido ou expirado!");
        }

        return subject;
    }
}
