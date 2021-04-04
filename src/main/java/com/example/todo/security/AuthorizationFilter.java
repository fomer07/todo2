package com.example.todo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.todo.security.SecurityConstants.*;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_NAME);

        if (header==null){
            chain.doFilter(request, response);
        return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = authenticate(request);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);

    }


    private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request){
        String token = request.getHeader(HEADER_NAME);

        if (token != null){
            Claims user = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        if (user != null){
            return new UsernamePasswordAuthenticationToken(user, null,new ArrayList<>());
        }else {
            return null;
        }

        }
        return null;
    }

}