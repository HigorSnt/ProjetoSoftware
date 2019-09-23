package ufcg.psoft.lab2.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;

@Service
public class JwtService {

    private UsuarioService service;
    private final int TOKEN_INDEX = 7;
    private final String TOKEN_KEY = "Xta1soF7UyJwk1VkecS89uh6HWEsimdi1hnsUDWz";

    public JwtService(UsuarioService service) {
        this.service = service;
    }

    public boolean userExists(String authorizationHeader) throws ServletException {
        String subject = getTokenUser(authorizationHeader);

        return this.service.getUsuario(subject).isPresent();
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
