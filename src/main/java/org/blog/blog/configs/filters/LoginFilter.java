package org.blog.blog.configs.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.blog.blog.configs.SecurityConstants;
import org.blog.blog.models.AccountCredentials;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    private final String secret = "secretWord";


    public LoginFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url), authenticationManager);
        setAuthenticationManager(authenticationManager);
    }

    private AccountCredentials accountCredentials;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        accountCredentials = new ObjectMapper().readValue(request.getInputStream(), AccountCredentials.class);

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        accountCredentials.getUsername(),
                        accountCredentials.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Date curentDate = new Date();
        String jwtToken = Jwts.builder()
                .setSubject(authResult.getName())
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET.getBytes())
                .setExpiration(new Date(curentDate.getTime() + SecurityConstants.JWT_EXPIRATION))
                .compact();
        response.addHeader("Authorization", "Bearer " + jwtToken);
        System.out.println("Token " + jwtToken);
    }
}