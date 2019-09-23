package ufcg.psoft.lab2.filters;

import io.jsonwebtoken.*;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends GenericFilterBean {

    private final int TOKEN_INDEX = 7;
    private final String TOKEN_KEY = "Xta1soF7UyJwk1VkecS89uh6HWEsimdi1hnsUDWz";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Token Inexistente ou Incorreto");
        }

        String token = header.substring(TOKEN_INDEX);

        try {
            Jwts.parser()
                    .setSigningKey(TOKEN_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException | ExpiredJwtException |
                MalformedJwtException | PrematureJwtException |
                UnsupportedJwtException | IllegalArgumentException e) {

            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
